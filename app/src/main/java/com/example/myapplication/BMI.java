package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.DecimalFormat;

public class BMI extends AppCompatActivity {
    Button btnTinh;
    EditText editTen, edtCao, edtNang, editBMI, editDoan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        btnTinh = findViewById(R.id.btnTinh);
        editTen = findViewById(R.id.edtTen);
        edtCao = findViewById(R.id.edtCao);
        edtNang = findViewById(R.id.edtNang);
        editBMI = findViewById(R.id.edtBMI);
        editBMI.setFocusable(false);
        editDoan = findViewById(R.id.edtDoan);
        editDoan.setFocusable(false);

        btnTinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double H = Double.parseDouble(edtCao.getText() + "");
                double W = Double.parseDouble(edtNang.getText() + "");
                double BMI = W / Math.pow(H, 2);
                String chandoan = "";
                if (BMI < 18)
                    chandoan = "Bạn gầy";
                else if (BMI <= 24.9)
                    chandoan = "Bạn bình thường";
                else if (BMI <= 29.9)
                    chandoan = "Bạn béo phì độ 1";
                else if (BMI <= 34.9)
                    chandoan = "Bạn béo phì độ 2";
                else
                    chandoan = "Bạn béo phì độ 3";

                DecimalFormat dcf = new DecimalFormat("#.0");
                editBMI.setText(dcf.format(BMI));
                editDoan.setText(chandoan);
            }
        });
    }
}