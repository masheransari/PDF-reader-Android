package com.example.asheransari.pdf_reader;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.ScrollBar;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.File;

public class pdf extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        PDFView pdfView = (PDFView) findViewById(R.id.pdfView);
        ScrollBar scrollBar = (ScrollBar)findViewById(R.id.scrollbar);
        pdfView.setScrollBar(scrollBar);
        scrollBar.setHorizontal(false);
        Intent i = getIntent();
        String path = i.getExtras().getString("PATH");

        File file = new File(path);
        if(file.canRead()){
            pdfView.fromFile(file).defaultPage(1).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(pdf.this, ""+nbPages, Toast.LENGTH_SHORT).show();
                }
            }).load();
        }
    }
}
