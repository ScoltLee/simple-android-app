package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SeePic extends AppCompatActivity {
    ImageView imgV;
    TextView tvsub;
    int image[] ={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,
            R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,R.drawable.pic10,
            R.drawable.pic11,R.drawable.pic12};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_pic);
        imgV = findViewById(R.id.imgV);
        tvsub = findViewById(R.id.tvsub);
        int tmp = getIntent().getIntExtra("pic",1);
        String name = getIntent().getStringExtra("name");
        imgV.setImageResource(image[tmp]);
        tvsub.setText(name);
    }
}