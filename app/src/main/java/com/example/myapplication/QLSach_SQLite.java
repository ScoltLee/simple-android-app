package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class QLSach_SQLite extends AppCompatActivity {
    ListView lv;
    public static SQLiteDatabase database = null;
    public static String DATABASE_NAME="qlsach.db";
    String DB_PATH_SUFFIX = "/databases/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsach_sqlite);
        processCopy();
        database = openOrCreateDatabase("qlsach.db",MODE_PRIVATE,null);

        addContol();
        addFind();
        addEvent();
    }

    private void addEvent() {

    }

    private void addContol() {
        lv = findViewById(R.id.lv);
        ArrayList<String> myList = new ArrayList<String>();
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,myList );
        lv.setAdapter(adapter);

        Cursor c = database.rawQuery("SELECT *FROM qlsach   ",null);
        c.moveToFirst();
        while(c.isAfterLast()==false){
            myList.add(c.getString(0)+" - " +c.getString(1)+" - "+c.getString(2)+" - "+
                    c.getString(3)+" - "+c.getInt(4));
            c.moveToNext();
        }
        c.close();
        adapter.notifyDataSetChanged();

    }

    private void addFind() {

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
}