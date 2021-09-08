package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Phone extends AppCompatActivity {
    String namephone[]={"Điện thoại Iphone 12", "Điện thoại SamSung S20","Điện thoại Nokia 6","Điện thoại Bphone 2020","Điện thoại Oppo 5","Điện thoại VSmart joy2"};
    int imagephone[] = {R.drawable.ip, R.drawable.samsung, R.drawable.htc,R.drawable.lg,R.drawable.wp,R.drawable.sky};
    String gia[]={"18,450,000 VND", "16,999,000 VND", "5,590,000 VND", "9,990,000 VND", "9,490,000 VND", "2,208,000 VND"};
    ArrayList<subPhone> myList;
    MyArrayAdapterForPhone myAdapter;
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        lv = findViewById(R.id.lv);
        myList = new ArrayList<>();
        for(int i=0; i< namephone.length;i++){
            myList.add(new subPhone(namephone[i],imagephone[i], gia[i]));
        }
        myAdapter = new MyArrayAdapterForPhone(this, R.layout.listviewphone, myList);
        lv.setAdapter(myAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Phone.this, SubListPhone.class);
                intent.putExtra("name",namephone[position]);
                intent.putExtra("gia",gia[position]);
                startActivity(intent);
            }
        });
    }
}