package com.example.myapplication;
import android.widget.ImageView;

public class TyGia {
    String type, imageurl, muatm, muack, bantm, banck;
    ImageView bitmap;

    public TyGia(String type, String imageurl, String muatm, String muack, String bantm,
                 String banck, ImageView bitmap) {
        this.type = type;
        this.imageurl = imageurl;
        this.muatm = muatm;
        this.muack = muack;
        this.bantm = bantm;
        this.banck = banck;
        this.bitmap = bitmap;
    }

    public TyGia() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getMuatm() {
        return muatm;
    }

    public void setMuatm(String muatm) {
        this.muatm = muatm;
    }

    public String getMuack() {
        return muack;
    }

    public void setMuack(String muack) {
        this.muack = muack;
    }

    public String getBantm() {
        return bantm;
    }

    public void setBantm(String bantm) {
        this.bantm = bantm;
    }

    public String getBanck() {
        return banck;
    }

    public void setBanck(String banck) {
        this.banck = banck;
    }

    public ImageView getBitmap() {
        return bitmap;
    }

    public void setBitmap(ImageView bitmap) {
        this.bitmap = bitmap;
    }
}

