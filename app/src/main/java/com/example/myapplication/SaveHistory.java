package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class SaveHistory extends AppCompatActivity {
    EditText edta, edtb, edtKQ;
    Button btnTong, btnClear;
    TextView tvLS;
    String lichsu="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_history);
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        edtKQ = findViewById(R.id.edtKQ);
        btnTong = findViewById(R.id.btnTong);
        btnClear = findViewById(R.id.btnClear);
        tvLS = findViewById(R.id.tvLS);

        SharedPreferences mypre = getSharedPreferences("mysave",MODE_PRIVATE);
        lichsu = mypre.getString("ls","");
        tvLS.setText(lichsu);

        btnTong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                int kq = a+b;
                edtKQ.setText(kq+"");
                lichsu +=a+" + "+b+" = "+kq+"\n";
                tvLS.setText(lichsu);
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lichsu="";
                tvLS.setText("");
            }
        });


    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences mypre = getSharedPreferences("mysave",MODE_PRIVATE);
        SharedPreferences.Editor myedit = mypre.edit();
        myedit.putString("ls",lichsu);
        myedit.commit();
    }
}