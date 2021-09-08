package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class Call extends AppCompatActivity {
    EditText edtPhone;
    ImageButton btnCall;
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        edtPhone = findViewById(R.id.edtPhone);
        btnCall = findViewById(R.id.btnCall);
        btnBack = findViewById(R.id.btnBack);

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + edtPhone.getText().toString()));
                if (ActivityCompat.checkSelfPermission(Call.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Call.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                    return;
                }
                startActivity(intentCall);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}