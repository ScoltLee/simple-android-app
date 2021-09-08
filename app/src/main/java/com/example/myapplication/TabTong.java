package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class TabTong extends AppCompatActivity {
    EditText edta, edtb;
    Button btnCong;
    ListView lv;
    ArrayList<String> list;
    ArrayAdapter<String> myarray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_tong);

        addControl();
        addEvent();
    }
    private void addEvent() {
        btnCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cong();
            }

            private void cong() {
                int a = Integer.parseInt(edta.getText().toString());
                int b = Integer.parseInt(edtb.getText().toString());
                String result = a+" + "+b+ " = "+(a+b);
                list.add(result);
                myarray.notifyDataSetChanged();
                edta.setText("");
                edtb.setText("");
            }
        });
    }

    private void addControl() {
        TabHost tab = (TabHost) findViewById(R.id.mytabhost);
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
        btnCong = findViewById(R.id.btnCong);
        lv = findViewById(R.id.lv);

        tab.setup();
        TabHost.TabSpec tab1, tab2;

        tab1 = tab.newTabSpec("t1");
        tab1.setContent(R.id.tab1);
        tab1.setIndicator("", getResources().getDrawable(R.drawable.cong));
        tab.addTab(tab1);

        tab2 = tab.newTabSpec("t2");
        tab2.setContent(R.id.tab2);
        tab2.setIndicator("", getResources().getDrawable(R.drawable.lichsu));
        tab.addTab(tab2);

        list = new ArrayList<String>();
        myarray = new ArrayAdapter<String>(TabTong.this, android.R.layout.simple_list_item_1, list);
        lv.setAdapter(myarray);
    }

}
