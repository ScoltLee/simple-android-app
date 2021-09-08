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

public class HoiDoaiArrayAdapter extends ArrayAdapter<TyGia> {
    int img[]={R.drawable.cny, R.drawable.aud,R.drawable.cad,R.drawable.chf,
            R.drawable.eur,R.drawable.gbp, R.drawable.hkd,R.drawable.jpy,
            R.drawable.xau,R.drawable.usd,R.drawable.thb,R.drawable.sgd,
            R.drawable.pnj_dab, R.drawable.nzd};
    Activity context;
    int resource;
    ArrayList<TyGia> object;

    public HoiDoaiArrayAdapter(@NonNull Activity context, int resource, @NonNull ArrayList<TyGia> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.object=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(resource,null);
        TyGia tyGia = object.get(position);

        ImageView imgIcon = (ImageView)convertView.findViewById(R.id.imgIcon);
        TextView tvMuaTM = (TextView)convertView.findViewById(R.id.tvMuaTM);
        TextView tvMuaCK = (TextView)convertView.findViewById(R.id.tvMuaCK);
        TextView tvBanCK= (TextView)convertView.findViewById(R.id.tvBanCK);
        TextView tvBanTM = (TextView)convertView.findViewById(R.id.tvBanTM);
        TextView tvMa = (TextView)convertView.findViewById(R.id.tvMa);

        int tmp=-1;
        switch (tyGia.getType()){
            case "CNY":
                tmp=0; break;
            case "AUD":
                tmp=1; break;
            case "CAD":
                tmp=2; break;
            case "CHF":
                tmp=3; break;
            case "EUR":
                tmp=4; break;
            case "GBP":
                tmp=5; break;
            case "HKD":
                tmp=6; break;
            case "JPY":
                tmp=7; break;
            case "XAU":
                tmp=8; break;
            case "USD":
                tmp=9; break;
            case "THB":
                tmp=10; break;
            case "SGD":
                tmp=11; break;
            case "PNJ_DAB":
                tmp=12; break;
            case "NZD":
                tmp=13; break;
        }
        imgIcon.setImageResource(img[tmp]);
        tvBanCK.setText(tyGia.getBanck());
        tvMuaCK.setText(tyGia.getMuack());
        tvBanTM.setText(tyGia.getBantm());
        tvMuaTM.setText(tyGia.getMuatm());
        tvMa.setText(tyGia.getType());

        return convertView;
    }
}


