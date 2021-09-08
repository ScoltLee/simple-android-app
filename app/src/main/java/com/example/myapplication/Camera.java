package com.example.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

public class Camera extends AppCompatActivity {
    ImageView img;
    ImageView btnCamera;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        img = findViewById(R.id.img);
        btnCamera = findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCamera = new Intent(ACTION_IMAGE_CAPTURE);
                if (ActivityCompat.checkSelfPermission(Camera.this,
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    ActivityCompat.requestPermissions(Camera.this,new
                            String[]{Manifest.permission.CAMERA}, 1);
                    return;
                }
                startActivityForResult(intentCamera,99);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable
            Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 99 && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(photo);
        }
    }
}