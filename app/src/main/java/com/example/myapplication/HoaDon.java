package com.example.myapplication;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class HoaDon extends AppCompatActivity {
    EditText edtten, edtsach, edttongkh, edttongvip, edttongdt;
    TextView tvtt;
    ImageView ibtnoff;
    Button btntinh, btntiep, btnthongke;
    CheckBox cbvip;
    int tongkh=0, tongvip =0;
    double doanhthu=0.0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don);
        edtten = findViewById(R.id.edtten);
        edtsach = findViewById(R.id.edtsach);
        edttongkh = findViewById(R.id.edttongkh);
        edttongvip = findViewById(R.id.edttongvip);
        edttongdt = findViewById(R.id.edttongdt);
        tvtt = findViewById(R.id.tvtt);
        ibtnoff = findViewById(R.id.ibtnoff);
        btntinh = findViewById(R.id.btntinh);
        btntiep = findViewById(R.id.btntiep);
        btnthongke = findViewById(R.id.btnthongke);
        cbvip = findViewById(R.id.cbvip);
        edttongdt.setFocusable(false);
        edttongkh.setFocusable(false);
        edttongvip.setFocusable(false);

        btntinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double a=1;
                if(cbvip.isChecked())
                    a=0.9;
                int soluong = Integer.parseInt(edtsach.getText()+"");
                tvtt.setText(((int)soluong*20000*a) +"");

            }
        });

        btntiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doanhthu += Double.parseDouble(tvtt.getText()+"");
                if(edtten.getText()!=null) tongkh+=1;
                if(cbvip.isChecked()) tongvip+=1;
                edtsach.setText("");
                edtten.setText("");
                cbvip.setChecked(false);
                tvtt.setText("");
                edtten.requestFocus();
            }
        });
        btnthongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttongkh.setText(tongkh+"");
                edttongvip.setText(tongvip+"");
                edttongdt.setText(doanhthu+"");
            }
        });

        ibtnoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder b =new AlertDialog.Builder(HoaDon.this);
                b.setTitle("Question");
                b.setMessage("Are you sure you want to exit?");
                b.setIcon(R.drawable.inform);
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        finish();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.cancel();
                    }
                });
                b.create().show();
            }
        });
    }
}