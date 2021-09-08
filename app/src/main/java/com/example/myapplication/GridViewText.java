package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class GridViewText extends AppCompatActivity {
    String arr[] = {"Ipad","Iphone","New Ipad","Sam Sung","Nokia","Sony Ericson","LG",
            "Q-Mobile","HTC","Blackberry","G Phone","FPT - Phone","HK Phone"};
    TextView tv;
    GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view_text);
        tv = findViewById(R.id.tv);
        gridView = findViewById(R.id.gridView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,arr);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tv.setText(arr[position]);
            }
        });
    }
}