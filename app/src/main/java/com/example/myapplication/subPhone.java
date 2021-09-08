package com.example.myapplication;

public class subPhone {
    private String namephone,gia;
    private int imagephone;

    public subPhone(String namephone, int imagephone, String gia) {
        this.namephone = namephone;
        this.imagephone = imagephone;
        this.gia = gia;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getNamePhone(){
        return namephone;
    }
    public void setNamePhone(String namephone){
        this.namephone = namephone;
    }
    public int getImagephone(){
        return imagephone;
    }
    public void setImagephone(int imagephone){
        this.imagephone = imagephone;
    }
}
