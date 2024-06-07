package com.example.dcu_image_viwer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView;
    ImageAdapter adapter;
    ArrayList<String> imageFiles;
    String imagePath = Environment.getExternalStorageDirectory().getPath() + "/Pictures/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, 1);

        imageFiles = new ArrayList<>();
        File[] files = new File(imagePath).listFiles();
        if (files != null) {
            for (File file : files) {
                String filename = file.getName();
                String ext = filename.substring(filename.length() - 3);
                if (ext.equals("jpg") || ext.equals("png") || ext.equals("jpeg")) {
                    imageFiles.add(imagePath + filename);
                }
            }
        }

        gridView = findViewById(R.id.gridViewImages);
        adapter = new ImageAdapter(this, imageFiles);
        gridView.setAdapter((ListAdapter) adapter);
    }
}