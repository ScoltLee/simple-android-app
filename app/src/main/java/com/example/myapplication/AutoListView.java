package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

public class AutoListView extends AppCompatActivity {
    TextView tv;
    AutoCompleteTextView ac;
    MultiAutoCompleteTextView mac;
    String arr[] ={"Hà Nội","Huế","Sài Gòn","Hà Giang","Hội An","Kiên Giang","Lâm Đồng", "Long Khánh"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_list_view);
        tv = findViewById(R.id.tv);
        ac = findViewById(R.id.singleComplete);
        mac = findViewById(R.id.multiComplete);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr);
        ac.setAdapter(adapter);

        mac.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,arr));
        mac.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        ac.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tv.setText(ac.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}