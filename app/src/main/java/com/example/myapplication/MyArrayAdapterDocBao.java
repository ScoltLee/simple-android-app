package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class MyArrayAdapterDocBao extends ArrayAdapter<ItemDocBao> {
    Activity context;
    int resource;
    ArrayList<ItemDocBao> arr;

    public MyArrayAdapterDocBao(Activity context, int resource, ArrayList<ItemDocBao> arr) {
        super(context, resource,arr);
        this.context = context;
        this.resource = resource;
        this.arr = arr;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflactor = context.getLayoutInflater();
        convertView = myInflactor.inflate(resource,null);
        ItemDocBao item = arr.get(position);

        TextView tvTieuDe = convertView.findViewById(R.id.tvTieuDe);
        tvTieuDe.setText(item.getTitle());

        TextView tvInfo = convertView.findViewById(R.id.tvInfo);
        tvInfo.setText(item.getInfo());

        ImageView img = convertView.findViewById(R.id.imgdb);
        img.setImageBitmap(item.getImg());

        DocBao.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(arr.get(position).getLink()));
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
