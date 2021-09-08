package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class SMS extends AppCompatActivity {
    EditText edtPhone2;
    ImageButton btnSent;
    Button btnBack2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        edtPhone2 = findViewById(R.id.edtPhone2);
        btnSent = findViewById(R.id.btnSent);
        btnBack2 = findViewById(R.id.btnBack2);

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + edtPhone2.getText().toString()));
                startActivity(intentSent);
            }
        });

        btnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}