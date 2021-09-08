package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;

public class SubKara extends AppCompatActivity {
    TextView tvMS, tvTenBH, tvTacGia,tvLoi;
    ImageButton btnThich, btnKoThich;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_kara);

        tvMS = (TextView) findViewById(R.id.tvMS);
        tvTenBH = (TextView) findViewById(R.id.tvTenBH);
        tvTacGia = (TextView) findViewById(R.id.tvTacGia);
        tvLoi = (TextView) findViewById(R.id.tvLoi);
        btnThich= (ImageButton) findViewById(R.id.btnThich);
        btnKoThich = (ImageButton) findViewById(R.id.btnKoThich);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("package");
        String maso = bundle.getString("maso");

        Cursor c = Karaoke.database.rawQuery("SELECT * FROM ArirangSongList WHERE MABH LIKE'"+maso+"'",null);
        tvMS.setText(maso);
        c.moveToFirst();
        tvTenBH.setText(c.getString(2));
        tvLoi.setText(c.getString(3));
        tvTacGia.setText((c.getString(4)));
        if(c.getInt(6)==0){
            btnThich.setVisibility(View.INVISIBLE);
            btnKoThich.setVisibility(View.VISIBLE);
        }
        else{
            btnThich.setVisibility(View.VISIBLE);
            btnKoThich.setVisibility(View.INVISIBLE);
        }
        c.close();

        btnThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH",0);
                Karaoke.database.update("ArirangSongList", values,"MABH=?",new String[]{tvMS.getText().toString()});
                btnThich.setVisibility(View.INVISIBLE);
                btnKoThich.setVisibility(View.VISIBLE);
            }
        });

        btnKoThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH",1);
                Karaoke.database.update("ArirangSongList", values,"MABH=?",new String[]{tvMS.getText().toString()});
                btnKoThich.setVisibility(View.INVISIBLE);
                btnThich.setVisibility(View.VISIBLE);
            }
        });
    }
}