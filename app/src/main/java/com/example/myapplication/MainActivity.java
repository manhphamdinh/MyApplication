package com.example.myapplication;

import static android.Manifest.permission.READ_MEDIA_IMAGES;

import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String fileContents = "Nội dung trong file thuộc thư mục của ứng dụng";
//        try {
//            FileOutputStream fos = openFileOutput("data.txt", MODE_PRIVATE);
//            fos.write(fileContents.getBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        try (FileInputStream fis = openFileInput("data.txt")){
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader reader = new BufferedReader(isr);
//            String data = reader.readLine();
//
//            Toast.makeText(this, data, Toast.LENGTH_LONG).show();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        SharedPreferences sf = getPreferences(MODE_PRIVATE);

        //Ghi du lieu
        SharedPreferences.Editor editor = sf.edit();
        editor.putString("hello", "manh");
        editor.apply();

        //Doc
        String hello = sf.getString("hello", "khong co du lieu");
        Toast.makeText(this, hello, Toast.LENGTH_LONG).show();

        checkAndRequestPermission();
        readImages();
    }

    private void readImages() {
        List<String> images = new ArrayList<>();
        Uri uri = MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL);
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
                    images.add(imagePath);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                cursor.close();
            }
        }
        Toast.makeText(this, "images: " + images.size(), Toast.LENGTH_LONG).show();
    }
    private static final int REQUEST_CODE_READ_MEDIA_IMAGES = 101;
    private boolean checkAndRequestPermission() {
        if (checkSelfPermission(READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{READ_MEDIA_IMAGES}, REQUEST_CODE_READ_MEDIA_IMAGES);
            return false;
        } else {
            return true;
        }
    }
}