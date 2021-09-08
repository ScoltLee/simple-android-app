package com.example.myapplication;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
public class MyArrayAdapterForPhone extends ArrayAdapter<subPhone> {
    Activity context;
    int idLayout;
    ArrayList<subPhone> myList;
    public MyArrayAdapterForPhone ( Activity context, int idLayout, ArrayList<subPhone> myList) {
        super(context, idLayout, myList);
        this.context = context;
        this.idLayout = idLayout;
        this.myList = myList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflactor = context.getLayoutInflater();
        convertView = myInflactor.inflate(idLayout,null);
        subPhone myphone = myList.get(position);

        ImageView imgphone = (ImageView) convertView.findViewById(R.id.ivt);
        imgphone.setImageResource(myphone.getImagephone());

        TextView txtnamephone = convertView.findViewById(R.id.tvt);
        txtnamephone.setText(myphone.getNamePhone());

        TextView txtgia = convertView.findViewById(R.id.tvGia);
        txtgia.setText(myphone.getGia()+"");
        return convertView;
    }
}

