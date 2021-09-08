package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class PTBac2 extends AppCompatActivity {
    Button btnTT, btnGiai, btnThoat;
    EditText edta, edtb, edtc;
    TextView txtkq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptbac2);
        btnTT = findViewById(R.id.btnTT);
        btnGiai = findViewById(R.id.btnGiai);
        btnThoat = findViewById(R.id.btnThoat);
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtc = findViewById(R.id.edtc);
        txtkq = findViewById(R.id.txtkq);

        btnGiai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sa = edta.getText() + "";
                String sb = edtb.getText() + "";
                String sc = edtc.getText() + "";
                int a = Integer.parseInt(sa);
                int b = Integer.parseInt(sb);
                int c = Integer.parseInt(sc);
                String kq = "";
                DecimalFormat dcf = new DecimalFormat("#.00");
                if (a == 0) {
                    if (b == 0) {
                        if (c == 0)
                            kq = "PT vô số nghiệm";
                        else
                            kq = "PT vô nghiệm";
                    } else {
                        kq = "Pt có 1 No, x=" + dcf.format(-c / b);
                    }
                } else {
                    double delta = b * b - 4 * a * c;
                    if (delta < 0) {
                        kq = "PT vô nghiệm";
                    } else if (delta == 0) {
                        kq = "Pt có No kép x1=x2=" + dcf.format(-b / (2 * a));
                    } else {
                        kq = "Pt có 2 No: x1=" + dcf.format((-b + Math.sqrt(delta)) / (2 * a)) + "; x2=" + dcf.format((-b - Math.sqrt(delta)) / (2 * a));
                    }
                }
                txtkq.setText(kq);
            }
        });

        btnTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edta.setText("");
                edtb.setText("");
                edtc.setText("");
                txtkq.setText("");
                edta.requestFocus();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}