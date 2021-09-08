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

public class ImageAdapter extends ArrayAdapter<ImageEx> {
    Activity context;
    int layout;
    ArrayList<ImageEx> imageList;

    public ImageAdapter(Activity context, int layout, ArrayList<ImageEx> imageList) {
        super(context, layout, imageList);
        this.context = context;
        this.layout = layout;
        this.imageList = imageList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater myInflactor = context.getLayoutInflater();
        convertView = myInflactor.inflate(layout,null);
        ImageEx myImage = imageList.get(position);

        ImageView img = convertView.findViewById(R.id.imageView);
        img.setImageResource(myImage.getPic());

        TextView tv = convertView.findViewById(R.id.tvimage);
        tv.setText(myImage.getName());

        return convertView;
    }
}
