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

public class ItemAdapter extends ArrayAdapter<ItemIcon> {
    Activity context;
    int id;
    ArrayList<ItemIcon> list;

    public ItemAdapter(Activity context, int id, ArrayList<ItemIcon> arr) {
        super(context, id, arr);
        this.context = context;
        this.id=id;
        this.list =arr;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflactor = context.getLayoutInflater();
        convertView=myInflactor.inflate(id,null);
        ItemIcon itemIcon = list.get(position);

        ImageView  imgV = convertView.findViewById(R.id.imgV);
        imgV.setImageResource(itemIcon.getIcon());

        TextView tv = convertView.findViewById(R.id.tvName);
        tv.setText(itemIcon.getName());

        return convertView;
    }
}
