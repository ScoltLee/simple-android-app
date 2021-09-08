package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Intent2 extends AppCompatActivity {
    Button btnKQ;
    EditText edta, edtb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);
        btnKQ = findViewById(R.id.btnKQ);
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        btnKQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent2.this, IntentChild2.class);
                Bundle bundle1 = new Bundle();
                int a = Integer.parseInt(edta.getText().toString()+"");
                int b = Integer.parseInt(edtb.getText().toString());
                bundle1.putInt("a",a);
                bundle1.putInt("b",b);
                intent1.putExtra("packet", bundle1);
                startActivity(intent1);
            }
        });
    }
}