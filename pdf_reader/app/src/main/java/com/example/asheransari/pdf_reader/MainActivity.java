package com.example.asheransari.pdf_reader;

import android.Manifest;
import android.app.ListActivity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gridView = (GridView) findViewById(R.id.gridView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                return;
            }
        }


        gridView.setAdapter(new CustomAdapter(MainActivity.this,
                GetFiles(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)))));
//TODO ok wala method hai yeh//
//        this.setListAdapter(new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, GetFiles(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)))));
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
        Toast.makeText(this, "File Exists", Toast.LENGTH_SHORT).show();

        return arrayList;
    }
}
