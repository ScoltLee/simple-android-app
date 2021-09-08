package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Karaoke extends AppCompatActivity {
    EditText edttim;
    ListView lv1, lv2, lv3;
    TabHost tab;
    ArrayList<Item> list1, list2,list3;
    MyArrayAdapter arr1, arr2,arr3;
    public static SQLiteDatabase database = null;
    public static String DATABASE_NAME="arirang.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    ImageButton btnRemove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_karaoke);
        processCopy();
        database = openOrCreateDatabase("arirang.sqlite",MODE_PRIVATE,null);

        addContol();
        addFind();
        addEvent();
    }

    private void addFind() {
        edttim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            private void getdata() {
                String dulieunhap = edttim.getText().toString();
                arr1.clear();
                if(!edttim.getText().toString().equals("")){
                    btnRemove.setVisibility(View.VISIBLE);
                    Cursor c = database.rawQuery("SELECT *FROM ArirangSongList WHERE TENBH1 LIKE '%"+dulieunhap+"%' OR MABH LIKE '%"+dulieunhap+"%'",null);
                    c.moveToFirst();
                    while(c.isAfterLast()==false){
                        list1.add(new Item(c.getString(1),c.getString(2),c.getInt(6)));
                        c.moveToNext();
                    }
                    c.close();
                }
                else
                    btnRemove.setVisibility(View.INVISIBLE);
                arr1.notifyDataSetChanged();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getdata();
            }


            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void processCopy() {
        File file = getDatabasePath(DATABASE_NAME);
        if(!file.exists()){
            try{
                CopyDataBaseFromAsset();
                Toast.makeText(this,"Copying sucess from Assets folder",Toast.LENGTH_LONG).show();
            }
            catch (Exception e){
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDataBaseFromAsset() {
        try{
            InputStream myInput;
            myInput = getAssets().open(DATABASE_NAME);

            String outFileName = getDatabasePath();
            File f = new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists())
                f.mkdir();
            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            while((length = myInput.read(buffer))>0){
                myOutput.write(buffer,0,length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
            ;
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    private String getDatabasePath() {
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }

    private void addEvent() {
        tab.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equalsIgnoreCase("t2")){
                    addDanhSach();
                }
                if(tabId.equalsIgnoreCase("t3")){
                    addYeuThich();
                }
            }


        });
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttim.setText("");
            }
        });
    }

    private void addYeuThich() {
        arr3.clear();
        Cursor c = database.rawQuery("SELECT *FROM ArirangSongList WHERE YEUTHICH=1",null);
        c.moveToFirst();
        while(c.isAfterLast()==false){
            list3.add(new Item(c.getString(1),c.getString(2),c.getInt(6)));
            c.moveToNext();
        }
        c.close();
        arr3.notifyDataSetChanged();
    }

    private void addDanhSach() {
        arr2.clear();
        Cursor c = database.rawQuery("SELECT *FROM ArirangSongList",null);
        c.moveToFirst();
        while(c.isAfterLast()==false){
            list2.add(new Item(c.getString(1),c.getString(2),c.getInt(6)));
            c.moveToNext();
        }
        c.close();
        arr2.notifyDataSetChanged();
    }

    private void addContol() {
        btnRemove = (ImageButton) findViewById(R.id.btnRemove);
        btnRemove.setVisibility(View.INVISIBLE);
        tab=(TabHost) findViewById(R.id.mytabhost);

        tab.setup();
        TabHost.TabSpec tab1=tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("",getResources().getDrawable((R.drawable.find)));
        tab.addTab(tab1);

        TabHost.TabSpec tab2=tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("",getResources().getDrawable((R.drawable.list)));
        tab.addTab(tab2);

        TabHost.TabSpec tab3=tab.newTabSpec("t3");
        tab3.setContent(R.id.tab3);
        tab3.setIndicator("",getResources().getDrawable((R.drawable.like1)));
        tab.addTab(tab3);

        edttim = findViewById(R.id.edtfind);
        lv1  = findViewById(R.id.lv1);
        lv2  = findViewById(R.id.lv2);
        lv3  = findViewById(R.id.lv3);

        list1 = new ArrayList<Item>();
        list2 = new ArrayList<Item>();
        list3= new ArrayList<Item>();

        arr1 = new MyArrayAdapter(Karaoke.this,R.layout.listitem,list1);
        arr2 = new MyArrayAdapter(Karaoke.this,R.layout.listitem,list2);
        arr3 = new MyArrayAdapter(Karaoke.this,R.layout.listitem,list3);

        lv1.setAdapter(arr1);
        lv2.setAdapter(arr2);
        lv3.setAdapter(arr3);
    }
}