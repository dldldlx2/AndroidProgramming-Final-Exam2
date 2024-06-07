package com.example.dcu_image_viwer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

import java.util.List;
import java.util.ArrayList;

public class TitleActivity extends AppCompatActivity {

    private static final int READ_MEDIA_IMAGES_PERMISSION_REQUEST = 1;

    private ImageView expandedImageView;
    private GridView gridView;
    private ImageAdapter adapter;
    private List<String> imagePaths;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);

        expandedImageView = findViewById(R.id.expandedImageView);
        gridView = findViewById(R.id.gridViewImages);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, READ_MEDIA_IMAGES_PERMISSION_REQUEST);
        } else {
            initializeImagePaths();
        }
    }

    private void initializeImagePaths() {
        imagePaths = getImagePathsFromMediaStore();

        String initialImagePath = getIntent().getStringExtra("image_path");
        if (initialImagePath != null) {

        }

        adapter = new ImageAdapter(this, imagePaths);
        gridView.setAdapter((ListAdapter) adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == READ_MEDIA_IMAGES_PERMISSION_REQUEST) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                initializeImagePaths();
            } else {
                Log.e("TitleActivity", "Permission denied. Cannot load images.");
            }
        }
    }

    private List<String> getImagePathsFromMediaStore() {
        List<String> paths = new ArrayList<>();
        return paths;
    }
}