package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class DocBao extends AppCompatActivity {
    static ListView lv;
    ArrayList<ItemDocBao> myList;
    MyArrayAdapterDocBao adapter;
    String nodeName, title = "", link = "", description = "", des = "", url = "";
    Bitmap mIcon_val = null;
    String URL = "https://vnexpress.net/rss/tin-moi-nhat.rss";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_bao);
        lv = findViewById(R.id.lvdocbao);
        myList = new ArrayList<ItemDocBao>();
        adapter = new MyArrayAdapterDocBao(this, R.layout.listitemdocbao, myList);
        lv.setAdapter(adapter);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        LoadExampleTask task = new LoadExampleTask();
        task.execute();
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<ItemDocBao>> {

        @Override
        protected ArrayList<ItemDocBao> doInBackground(Void... voids) {
            myList = new ArrayList<ItemDocBao>();
            try {
                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser parser = fc.newPullParser();
                XMLParser myparser = new XMLParser();
                String xml = myparser.getXMLFromURL(URL);
                parser.setInput(new StringReader(xml));
                int eventType = -1;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    eventType = parser.next();
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("title")) {
                                title = parser.nextText();
                            } else if (nodeName.equals("link")) {
                                link = parser.nextText();
                            } else if (nodeName.equals("description")) {
                                description = parser.nextText();
                                try {
                                    url = description.substring((description.indexOf("src=") + 5),
                                            (description.indexOf("></a") - 2));
                                } catch (Exception e1) {
                                    e1.printStackTrace();
                                }
                                des = description.substring(description.indexOf("</br>")+5);
                                try {
                                    java.net.URL newURL = new URL(url.toString() + "");
                                    mIcon_val =  BitmapFactory.decodeStream(newURL.openConnection().getInputStream());
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equals("item")) {
                                myList.add(new ItemDocBao(mIcon_val, title, des, link));
                            }
                            break;
                    }
                }
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return myList;
        }
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();
            adapter.clear();
        }
        @Override
        protected void onPostExecute(ArrayList<ItemDocBao> result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            progressBar.setVisibility(View.INVISIBLE);
            adapter.clear();
            adapter.addAll(result);
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
        }
    }
}