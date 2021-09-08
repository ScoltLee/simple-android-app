package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class IntentChild2 extends AppCompatActivity {
    TextView tvkq;
    Button btnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_child2);
        tvkq = findViewById(R.id.tvkq);
        btnback = findViewById(R.id.btnBack);
        Intent getIntent1 = getIntent();
        Bundle getBundle = getIntent1.getBundleExtra("packet");
        int a = getBundle.getInt("a");
        int b = getBundle.getInt("b");
        if(a==b && a==0)
            tvkq.setText("PT vô số nghiệm");
        else
        if(a==0 && b!=0)
            tvkq.setText("PT vô nghiệm");
        else{
            DecimalFormat dcf = new DecimalFormat("0.##");
            tvkq.setText(dcf.format(-b*1.0/a)+"");
        }
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}