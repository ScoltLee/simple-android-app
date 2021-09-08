package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class ParseJson extends AppCompatActivity {
    Button btnGet;
    ListView lv;
    ArrayList<String> myList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json);
        btnGet = findViewById(R.id.btnGet);
        lv = findViewById(R.id.lv);
        myList = new ArrayList<>();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myList);
        lv.setAdapter(adapter);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseJson();
            }

            private void parseJson() {
                String json = null;
                try{
                    InputStream input =getAssets().open("computer.json");
                    int  size = input.available();
                    byte[] buffer = new byte[size];
                    input.read(buffer);
                    input.close();
                    json = new String(buffer,"UTF-8");
                    JSONObject reader = new JSONObject(json);
                    myList.add(reader.getString("MaDM"));
                    myList.add(reader.getString("TenDM"));
                    JSONArray arr = reader.getJSONArray("Sanphams");
                    for(int i = 0; i<arr.length();i++){
                        JSONObject myobj = arr.getJSONObject(i);
                        myList.add(myobj.getString("MaSP")+" - "+myobj.getString("TenSP"));
                        myList.add(myobj.getString("SoLuong")+" * "+myobj.getString("DonGia")+
                                " = "+myobj.getString("ThanhTien"));
                        myList.add(myobj.getString("Hinh"));
                    }
                    adapter.notifyDataSetChanged();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}