package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class ConvertF extends AppCompatActivity {
    Button btncf, btnfc, btncl;
    EditText edtDoC, edtDoF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert_f);
        edtDoC = findViewById(R.id.edtDoC);
        edtDoF = findViewById(R.id.edtDoF);
        btncf = findViewById(R.id.btnDoF);
        btnfc = findViewById(R.id.btnDoC);
        btncl = findViewById(R.id.btnClear);

        btncf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dcf = new DecimalFormat("#.00");
                String doC = edtDoC.getText()+"";
                int C = Integer.parseInt(doC);
                edtDoF.setText(""+dcf.format(C*1.8+32));
            }
        });

        btnfc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat dcf=new DecimalFormat("#.00");
                String doF = edtDoF.getText()+"";
                int F = Integer.parseInt(doF);
                edtDoC.setText(""+dcf.format((F-32)/1.8));
            }
        });
        btncl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtDoC.setText("");
                edtDoF.setText("");
                edtDoF.requestFocus();
            }
        });
    }
}