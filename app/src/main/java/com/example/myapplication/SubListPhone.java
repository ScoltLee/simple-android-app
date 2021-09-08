package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SubListPhone extends AppCompatActivity {
    TextView tvsubphone, tvgia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_list_phone);
        tvsubphone = findViewById(R.id.tvsubphone);
        tvgia = findViewById(R.id.tvsubgia);

        Intent intent = getIntent();
        String namephone = intent.getStringExtra("name");
        tvsubphone.setText(namephone);
        tvgia.setText(intent.getStringExtra("gia"));
    }
}