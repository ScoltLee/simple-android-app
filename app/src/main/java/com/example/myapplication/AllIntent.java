package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AllIntent extends AppCompatActivity {
ImageView btnIntent1, btnIntent2, btnIntent3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_intent);
        btnIntent1 = findViewById(R.id.btnIntent1);
        btnIntent2 = findViewById(R.id.btnIntent2);
        btnIntent3 = findViewById(R.id.btnIntent3);

        btnIntent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(AllIntent.this, Intent1.class);
                startActivity(intent1);
            }
        });

        btnIntent2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AllIntent.this, Intent2.class);
                startActivity(intent2);
            }
        });

        btnIntent3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(AllIntent.this, Intent3.class);
                startActivity(intent3);
            }
        });
    }
}