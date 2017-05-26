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

public class MainActivity extends AppCompatActivity  {
    ArrayAdapter adapter;
    int clickCounter=0;
    ArrayList listItems=new ArrayList();
    private File[] imagelist;
    String[] pdflist;
    int MY_PERMISSIONS_REQUEST_READ_CONTACTS =1;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final GridView gridView = (GridView)findViewById(R.id.gridView);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {

                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant

                return;
            }
        }
//        File images = Environment.getExternalStorageDirectory();
//        imagelist = images.listFiles(new FilenameFilter(){
//            public boolean accept(File dir, String name)
//            {
//                return ((name.endsWith(".pdf")));
//            }
//        });
//        pdflist = new String[imagelist.length];
//        for(int i = 0;i<imagelist.length;i++)
//        {
//            pdflist[i] = imagelist[i].getName();
//        }
//        Log.e("name",""+imagelist);
//        this.setListAdapter(new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, pdflist



        gridView.setAdapter(new CustomAdapter(MainActivity.this,GetFiles(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)))));
//TODO ok wala method hai yeh//
//        this.setListAdapter(new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, GetFiles(String.valueOf(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)))));
    }

    public ArrayList<PDFDoc> GetFiles(String DirectoryPath) {
//        ArrayList<String> MyFiles = new ArrayList<String>();
        ArrayList<PDFDoc> arrayList = new ArrayList<>();
        File f = new File(DirectoryPath);
        PDFDoc pdfDoc;
//        f.mkdirs();
        File[] files = f.listFiles();
        if (files.length == 0) {
            Toast.makeText(this, "No File Exists", Toast.LENGTH_SHORT).show();
            return null;
        }
        else {
            for (int i=0; i<files.length; i++)
                if (files[i].getName().endsWith(".pdf")) {
                    //here you have that file.
//                    pdf_names.add(FileList[i].getName());
//                    MyFiles.add(files[i].getName());
                    pdfDoc = new PDFDoc();
                    pdfDoc.setName(files[i].getName());
                    pdfDoc.setPath(files[i].getAbsolutePath());
                    arrayList.add(pdfDoc);
            }

        }
        Toast.makeText(this, "File Exists", Toast.LENGTH_SHORT).show();

        return arrayList;
    }
//    @Override
//    protected void onListItemClick(ListView l, View v, int position, long id) {
//        super.onListItemClick(l, v, position, id);
//        PackageManager packageManager = getPackageManager();
//        Intent testIntent = new Intent(Intent.ACTION_VIEW);
//        testIntent.setType("application/pdf");
//        List list = packageManager.queryIntentActivities(testIntent, PackageManager.MATCH_DEFAULT_ONLY);
//
//        if (list.size() > 0 && imagelist[(int) id].isFile()) {
//            Intent intent = new Intent();
//            intent.setAction(Intent.ACTION_VIEW);
//            Uri uri = Uri.fromFile(imagelist[(int) id].getAbsoluteFile());
//            intent.setDataAndType(uri, "application/pdf");
//            startActivity(intent);
//        }
//    }

}

//
////    File path;
////    ListView list;
////    private static ArrayList<String> pdf_paths = new ArrayList<>();
////    private static ArrayList<String> pdf_name = new ArrayList<>();
////
////    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_main);
////
////        list = (ListView) findViewById(R.id.listView1);
////        pdf_paths.clear();
////        pdf_name.clear();
////
////        //Access External Storage..
////        path = new File(Environment.getExternalStorageDirectory() + "");
////
////        searchFolderRecursive(path);
////
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,
////                android.R.id.text1, pdf_name);
////        list.setAdapter(adapter);
////
////        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
////                String path = pdf_paths.get(i);
//////                passing Intent...
////            }
////        });
////    }
////
////    private static void searchFolderRecursive(File folder) {
////        if (folder != null) {
////            if (folder.listFiles() != null) {
////                for (File file : folder.listFiles()) {
////                    //this is for PDF files...
////                    if (file.isFile()) {
////                        if (file.getName().contains(".pdf")) {
////                            file.getPath();
////                            pdf_name.add(file.getName());
////                            pdf_paths.add(file.getAbsolutePath());
////                        }
////                    } else {
////                        searchFolderRecursive(file);
////                    }
////
////                }
////            }
////        }
////    }
////}
//
////    File path;
////    ListView list;
////    static ArrayList<String> pdf_paths = new ArrayList<String>();
////    static ArrayList<String> pdf_names = new ArrayList<String>();
//
//    private static final int MY_PERMISSION_REQUEST = 1;
//    ArrayList<String> arrayList;
//    ListView listView;
//    ArrayAdapter<String> adapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//
//dostuff();
//
////        list = (ListView) findViewById(R.id.listView1);
////        pdf_paths.clear();
////        pdf_names.clear();
////
//////Access External storage
//////        path = new File(Environment.getExternalStorageDirectory() + "");
////        searchFolderRecursive1(new File(Environment.getExternalStorageDirectory().getPath()));
////
////        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
////                android.R.layout.simple_list_item_1, android.R.id.text1, pdf_names);
////        Log.e("aaaaaaaaaa", "" + pdf_names);
////
////        list.setAdapter(adapter);
////        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////
////            @Override
////            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
////                                    long arg3) {
////// TODO Auto-generated method stub
////                String path = pdf_paths.get(arg2);
//////                File file = new File(path);
//////                Intent intent = new Intent(Intent.ACTION_VIEW);
//////                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
//////                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
//////                startActivity(intent);
////            }
////        });
//    }
//
//    public void searchFolderRecursive1(File dir) {
//        String pdfPattern = ".pdf";
//
//        File FileList[] = dir.listFiles();
//
//        if (FileList != null) {
//            for (int i = 0; i < FileList.length; i++) {
//
//                if (FileList[i].isDirectory()) {
//                    searchFolderRecursive1(FileList[i]);
//                } else {
//                    if (FileList[i].getName().endsWith(pdfPattern)) {
//                        //here you have that file.
//                        pdf_names.add(FileList[i].getName());
//                    }
//                }
//            }
//        }
//    }
////    private static void searchFolderRecursive1(File folder) {
////        if (folder != null) {
////            if (folder.listFiles() != null) {
////                for (File file : folder.listFiles()) {
////                    if (file.isFile()) {
////                        //.pdf files
////                        if (file.getName().contains(".pdf")) {
////                            Log.e("ooooooooooooo", "path__=" + file.getName());
////                            file.getPath();
////                            pdf_names.add(file.getName());
////                            pdf_paths.add(file.getPath());
////                            Log.e("pdf_paths", "" + pdf_names);
////                        }
////                    } else {
////                        searchFolderRecursive1(file);
////                    }
////                }
////            }
////        }
////    }
//
//
//}
