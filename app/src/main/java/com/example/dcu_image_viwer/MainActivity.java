package com.example.dcu_image_viwer;

import android.Manifest;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.BaseAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    File[] imageFiles;
    ArrayList<String> ItemImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_MEDIA_IMAGES}, MODE_PRIVATE);

        ListView listView = findViewById(R.id.listViewImage);

        imageFiles = new File("/sdcard/").listFiles();
        ItemImage = new ArrayList<>();

        for (File file : imageFiles) {
            String fileName = file.getName();
            String extName = fileName.substring(fileName.length() - 3).toLowerCase();
            if (extName.equals("jpg") || extName.equals("png") || extName.equals("jpeg")) {
                ItemImage.add(file.getAbsolutePath());
            }
        }

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(getApplicationContext(), ViewerActivity.class);
            intent.putExtra("image_path", ItemImage.get(position));
            startActivity(intent);
        });

        MainAdapter listAdapt = new MainAdapter(this, ItemImage);
        listView.setAdapter(listAdapt);
    }

    class MainAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<String> imageList;

        public MainAdapter(Context context, ArrayList<String> itemimage) {
            this.context = context;
            this.imageList = itemimage;
        }

        @Override
        public int getCount() {
            return imageList.size();
        }

        @Override
        public Object getItem(int position) {
            return imageList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(context).inflate(R.layout.item_image, parent, false);
                holder = new ViewHolder();
                holder.imageView1 = convertView.findViewById(R.id.image1);
                holder.imageView2 = convertView.findViewById(R.id.image2);
                holder.imageView3 = convertView.findViewById(R.id.image3);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            try {
                int startIndex = position * 3;

                if (startIndex < imageList.size()) {
                    File file1 = new File(imageList.get(startIndex));
                    if (file1.exists()) {
                        Bitmap bitmap = BitmapFactory.decodeFile(file1.getAbsolutePath());
                        holder.imageView1.setImageBitmap(bitmap);
                    } else {
                        holder.imageView1.setImageBitmap(null);
                    }
                } else {
                    holder.imageView1.setImageBitmap(null);
                }

                int secondIndex = startIndex + 1;
                if (secondIndex < imageList.size()) {
                    File file2 = new File(imageList.get(secondIndex));
                    if (file2.exists()) {
                        Bitmap bitmap = BitmapFactory.decodeFile(file2.getAbsolutePath());
                        holder.imageView2.setImageBitmap(bitmap);
                    } else {
                        holder.imageView2.setImageBitmap(null);
                    }
                } else {
                    holder.imageView2.setImageBitmap(null);
                }

                int thirdIndex = startIndex + 2;
                if (thirdIndex < imageList.size()) {
                    File file3 = new File(imageList.get(thirdIndex));
                    if (file3.exists()) {
                        Bitmap bitmap = BitmapFactory.decodeFile(file3.getAbsolutePath());
                        holder.imageView3.setImageBitmap(bitmap);
                    } else {
                        holder.imageView3.setImageBitmap(null);
                    }
                } else {
                    holder.imageView3.setImageBitmap(null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return convertView;
        }

        class ViewHolder {
            ImageView imageView1;
            ImageView imageView2;
            ImageView imageView3;
        }
    }
}