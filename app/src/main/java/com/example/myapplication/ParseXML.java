package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;

public class ParseXML extends AppCompatActivity {
    Button btnGet;
    ListView lv;
    ArrayList<String> myList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_xml);
        btnGet = findViewById(R.id.btnGet);
        lv = findViewById(R.id.lv);
        myList = new ArrayList<>();
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,myList);
        lv.setAdapter(adapter);

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parseXML();
            }

            private void parseXML() {
                String xml = null;
                try{
                    InputStream input =getAssets().open("employee.xml");
                    XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = fc.newPullParser();
                    parser.setInput(input,null);
                    int eventType = -1;
                    String nodeName;
                    String data = "";
                    while(eventType != XmlPullParser.END_DOCUMENT){
                        eventType = parser.next();
                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                break;
                            case XmlPullParser.START_TAG:
                                nodeName = parser.getName();
                                if(nodeName.equals("employee")){
                                    data+= parser.getAttributeValue(0)+" - ";
                                    data+= parser.getAttributeValue(1)+" - ";
                                }else if(nodeName.equals("name")){
                                    parser.next();
                                    data += parser.getText() + " - ";
                                } else if(nodeName.equals("phone")){
                                    parser.next();
                                    data += parser.getText();
                                }
                                break;
                            case XmlPullParser.END_TAG:
                                nodeName= parser.getName();
                                if(nodeName.equals("employee")){
                                    myList.add(data);
                                    data = "";
                                }
                                break;
                        }
                        adapter.notifyDataSetChanged();
                    }
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}