package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int icon[]={R.drawable.calculate,R.drawable.circle,R.drawable.calculator, R.drawable.fahrenheit,
                R.drawable.schedule,R.drawable.ptbac2, R.drawable.money, R.drawable.info,
                R.drawable.order, R.drawable.intent, R.drawable.contact, R.drawable.cameraicon,
                R.drawable.music,R.drawable.browser2,R.drawable.notes,R.drawable.spinner,
                R.drawable.listview, R.drawable.auto,R.drawable.grid,R.drawable.phone,
                R.drawable.burst, R.drawable.sigma, R.drawable.student, R.drawable.books,
                R.drawable.all, R.drawable.iconkara, R.drawable.docbao, R.drawable.json, R.drawable.xml,
                R.drawable.progress, R.drawable.exchange};
        String name[]={"Sub Basic","Circle Life","BMI","Convert T","Convert Schedule","Equation 2","Convert M"
                ,"Sơ Yếu LL","Order","Intent","Contact","Camera","Music","Browser","Notes","Spinner Layout","Auto List View",
                "Grid View Phone","Grid View Text","List View Phone","Grid View Image", "Tab Tổng",
                "QLSV SQLite","QLSach SQLite","Save Total Pref","Karaoke","Magazine","Parse Json",
                "Parse XML","Async Task1","Async Task2"};
        ArrayList<ItemIcon> arr = new ArrayList<ItemIcon>();
        for(int i=0;i<icon.length;i++){
            arr.add(new ItemIcon(icon[i],name[i]));
        }
        ItemAdapter adapter = new ItemAdapter(this, R.layout.item,arr);
        gv = findViewById(R.id.gv);
        gv.setAdapter(adapter);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch(position){
                    case 0:
                        intent = new Intent(MainActivity.this,Calculator.class);
                        break;
                    case 1:
                        intent = new Intent(MainActivity.this, Cycle.class);
                        break;
                    case 2:
                        intent = new Intent(MainActivity.this,BMI.class);
                        break;
                    case 3:
                        intent = new Intent(MainActivity.this, ConvertF.class);
                        break;
                    case 4:
                        intent = new Intent(MainActivity.this, ChuyenDoiLich.class);
                        break;
                    case 5:
                        intent = new Intent(MainActivity.this, PTBac2.class);
                        break;
                    case 6:
                        intent = new Intent(MainActivity.this, ChuyenDoiTien.class);
                        break;
                    case 7:
                        intent = new Intent(MainActivity.this, TongHop.class);
                        break;
                    case 8:
                        intent = new Intent(MainActivity.this, HoaDon.class);
                        break;
                    case 9:
                        intent = new Intent(MainActivity.this, AllIntent.class);
                        break;
                    case 10:
                        intent = new Intent(MainActivity.this, CallSMS.class);
                        break;
                    case 11:
                        intent = new Intent(MainActivity.this, Camera.class);
                        break;
                    case 12:
                        intent = new Intent(MainActivity.this, Music.class);
                        break;
                    case 13:
                        intent = new Intent(MainActivity.this, Search.class);
                        break;
                    case 14:
                        intent = new Intent(MainActivity.this, Notes.class);
                        break;
                    case 15:
                        intent = new Intent(MainActivity.this, SpinnerLayout.class);
                        break;
                    case 16:
                        intent = new Intent(MainActivity.this, ListViewTest.class);
                        break;
                    case 17:
                        intent = new Intent(MainActivity.this, AutoListView.class);
                        break;
                    case 18:
                        intent = new Intent(MainActivity.this, GridViewText.class);
                        break;
                    case 19:
                        intent = new Intent(MainActivity.this, Phone.class);
                        break;
                    case 20:
                        intent = new Intent(MainActivity.this, GridViewImage.class);
                        break;
                    case 21:
                        intent = new Intent(MainActivity.this, TabTong.class);
                        break;
                    case 22:
                        intent = new Intent(MainActivity.this, QLSV_SQLite.class);
                        break;
                    case 23:
                        intent = new Intent(MainActivity.this, QLSach_SQLite.class);
                        break;
                    case 24:
                        intent = new Intent(MainActivity.this, SaveHistory.class);
                        break;
                    case 25:
                        intent = new Intent(MainActivity.this, Karaoke.class);
                        break;
                    case 26:
                        intent = new Intent(MainActivity.this, DocBao.class);
                        break;
                    case 27:
                        intent = new Intent(MainActivity.this, ParseJson.class);
                        break;
                    case 28:
                        intent = new Intent(MainActivity.this, ParseXML.class);
                        break;
                    case 29:
                        intent = new Intent(MainActivity.this, AsyncTask1.class);
                        break;
                    case 30:
                        intent = new Intent(MainActivity.this, AsyncTask2.class);
                        break;
                }
                startActivity(intent);
            }
        });

    }
}