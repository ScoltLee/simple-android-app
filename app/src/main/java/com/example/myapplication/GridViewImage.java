package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;


public class GridViewImage extends AppCompatActivity {
    GridView gridView;
    ArrayList<ImageEx> arrImage;
    ImageAdapter adapter;
    int image[] ={R.drawable.pic1,R.drawable.pic2,R.drawable.pic3,R.drawable.pic4,R.drawable.pic5,
            R.drawable.pic6,R.drawable.pic7,R.drawable.pic8,R.drawable.pic9,R.drawable.pic10,
            R.drawable.pic11,R.drawable.pic12};
    String name[]={"Ảnh 1","Ảnh 2","Ảnh 3","Ảnh 4","Ảnh 5","Ảnh 6","Ảnh 7","Ảnh 8","Ảnh 9","Ảnh 10",
            "Ảnh 11","Ảnh 12"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_image);

        gridView = findViewById(R.id.gridView);
        arrImage = new ArrayList<>();
        for(int i=0;i<image.length;i++)
            arrImage.add(new ImageEx(name[i],image[i]));

        adapter = new ImageAdapter(this, R.layout.imageline ,arrImage);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GridViewImage.this, SeePic.class);
                intent.putExtra("pic",position);
                intent.putExtra("name",name[position]);
                startActivity(intent);
            }
        });
    }


}