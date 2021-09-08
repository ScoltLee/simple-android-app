package com.example.myapplication;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<Item> {
    Activity context = null;
    ArrayList<Item> myArray = null;
    int layoutid;

    public MyArrayAdapter(Activity context, int layoutid, ArrayList<Item> myArray) {
        super(context, layoutid, myArray);
        this.context = context;
        this.myArray = myArray;
        this.layoutid = layoutid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(layoutid,null);

        final Item myItem = myArray.get(position);
        final ImageView btnLike = (ImageView)convertView.findViewById(R.id.btnLike);
        final TextView tieude = (TextView) convertView.findViewById(R.id.tieude);
        tieude.setText(myItem.getTieude());
        final  TextView maso =(TextView)convertView.findViewById(R.id.maso);
        maso.setText(myItem.getMaso());
        final ImageView btnUnLike =(ImageView)convertView.findViewById(R.id.btnUnLike);
        int like = myItem.getThich();

        if(like==0){
            btnLike.setVisibility(View.INVISIBLE);
            btnUnLike.setVisibility(View.VISIBLE);
        }
        else{
            btnUnLike.setVisibility(View.INVISIBLE);
            btnLike.setVisibility(View.VISIBLE);
        }

        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH",0);
                Karaoke.database.update("ArirangSongList", values,"MABH=?",new String[]{maso.getText().toString()});
                btnLike.setVisibility(View.INVISIBLE);
                btnUnLike.setVisibility(View.VISIBLE);
            }
        });

        btnUnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH",1);
                Karaoke.database.update("ArirangSongList", values,"MABH=?",new String[]{maso.getText().toString()});
                btnLike.setVisibility(View.VISIBLE);
                btnUnLike.setVisibility(View.INVISIBLE);
            }
        });

        tieude.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                tieude.setTextColor(Color.RED);
                maso.setTextColor(Color.RED);
                Intent intent = new Intent(context,SubKara.class);
                Bundle bundle = new Bundle();
                bundle.putString("maso",maso.getText().toString());
                intent.putExtra("package",bundle);
                context.startActivity(intent);

            }
        });
        return convertView;
    }
}