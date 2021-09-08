package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Intent3 extends AppCompatActivity {
    EditText edta, edtb;
    Button btnKQ;
    TextView tvkq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent3);
        edta = findViewById(R.id.edta2);
        edtb = findViewById(R.id.edtb2);
        btnKQ = findViewById(R.id.btnKQ);
        tvkq = findViewById(R.id.tvkq);

        btnKQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent3.this, IntentChild3.class);
                int a = Integer.parseInt(edta.getText().toString()+"");
                int b = Integer.parseInt(edtb.getText().toString()+"");
                intent.putExtra("a", a);
                intent.putExtra("b", b);
                startActivityForResult(intent,99);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==99 &&resultCode==33){
            tvkq.setText(data.getIntExtra("kq",1)+"");
        }
    }
}