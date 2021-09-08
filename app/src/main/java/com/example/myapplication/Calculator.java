package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Calculator extends AppCompatActivity {
    EditText edta,edtb, edkq;
    Button bttong, bthieu, btthuong, bttich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edkq = findViewById(R.id.edkq);
        edkq.setFocusable(false);
        bttong = findViewById(R.id.bttong);
        bthieu = findViewById(R.id.bthieu);
        btthuong = findViewById(R.id.btthuong);
        bttich = findViewById(R.id.bttich);

        bttong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int c = a+b;
                edkq.setText("Tổng = "+c);
                edta.requestFocus();
                edta.selectAll();
            }
        });
        bthieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int c = a-b;
                edkq.setText("Hiệu = "+ c);
                edta.requestFocus();
                edta.selectAll();
            }
        });
        btthuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int c = a/b;
                edkq.setText("Thương = "+c);
                edta.requestFocus();
                edta.selectAll();
            }
        });
        bttich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int c = a*b;
                edkq.setText("Tích = "+c);
                edta.requestFocus();
                edta.selectAll();
            }
        });
    }
}