package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IntentChild3 extends AppCompatActivity {
    EditText edta2, edtb2;
    Button btnKQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_child3);
        edta2 = findViewById(R.id.edta2);
        edtb2 = findViewById(R.id.edtb2);
        btnKQ = findViewById(R.id.btnKQ);
        int a = getIntent().getIntExtra("a",1);
        int b = getIntent().getIntExtra("b",1);
        edta2.setText(a+"");
        edtb2.setText(b+"");
        btnKQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getIntent().putExtra("kq", a+b);
                setResult(33,getIntent());
                finish();
            }
        });
    }
}