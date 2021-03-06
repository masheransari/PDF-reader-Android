package com.example.asheransari.pdf_reader;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    boolean check = false;
    GridView gridView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                    != PackageManager.PERMISSION_GRANTED) {
//
//                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
//                return;
//            }
//        }
        isStoragePermissionGranted();
//        if(true == Manifest.permission.READ_EXTERNAL_STORAGE){}
//        = isStoragePermissionGranted();
        check = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                check = true;
            }
        }
        Log.e("CheckStatus", "" + check);

        showInGrid(check, gridView);


//TODO ok wala method hai yeh//
//        this.setListAdapter(new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, GetFiles(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)))));
    }

    private void showInGrid(boolean check, GridView gridV) {
        gridV = (GridView) findViewById(R.id.gridView);
        if (check) {
            gridV.setAdapter(new CustomAdapter(MainActivity.this,
                    GetFiles(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)))));
        } else {
            Toast.makeText(this, "Storage Read Permission Denial", Toast.LENGTH_LONG).show();
        }
    }

    public void isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("Premission", "Permission is granted");
//                return true;
            } else {

                Log.v("Permission", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//                return false;
            }
        } else {
            //permission is automatically granted on sdk<23 upon installation
            Log.v("Premission", "Permission is granted");
//            return true;
        }
    }

    public ArrayList<PDFDoc> GetFiles(String DirectoryPath) {
        ArrayList<PDFDoc> arrayList = new ArrayList<>();
        File f = new File(DirectoryPath);
        PDFDoc pdfDoc;
        File[] files = f.listFiles();
        if (files.length == 0) {
            Toast.makeText(this, "No File Exists", Toast.LENGTH_SHORT).show();
            return null;
        } else {
            for (int i = 0; i < files.length; i++)
                if (files[i].getName().endsWith(".pdf")) {
                    pdfDoc = new PDFDoc();
                    pdfDoc.setName(files[i].getName());
                    pdfDoc.setPath(files[i].getAbsolutePath());
                    arrayList.add(pdfDoc);
                }

        }
//        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Toast.makeText(this, "File Exists", Toast.LENGTH_SHORT).show();

        return arrayList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.refreshId:
                showInGrid(check, gridView);
        }

        return super.onOptionsItemSelected(item);
    }
}
