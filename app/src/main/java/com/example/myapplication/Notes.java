package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Notes extends AppCompatActivity {
    EditText edtWork, edtH, edtM;
    TextView tvDay;
    Button btnAdd;
    ListView lv;
    ArrayList <String> arr;
    ArrayAdapter<String> arrAdap;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes  );

        edtWork = findViewById(R.id.edtWork);
        edtH = findViewById(R.id.edtH);
        edtM = findViewById(R.id.edtM);
        btnAdd = findViewById(R.id.btnAdd);
        lv = findViewById(R.id.lv);
        tvDay = findViewById(R.id.tvDay);

        arr = new ArrayList<String>();
        arrAdap = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,arr);
        lv.setAdapter(arrAdap);
        initPreferences();
        total = sharedPreferences.getInt("total",0);
        int check = 0, a=0;
        while(check<=total){
            String Stmp = sharedPreferences.getString("data"+(a), "");
            if(Stmp!="") {
                String savedData = Stmp;
                arr.add(savedData);
                arrAdap.notifyDataSetChanged();
            }
            a++;
            check++;
        }

        Date currentDate = Calendar.getInstance().getTime();
        java.text.SimpleDateFormat formatDate = new java.text.SimpleDateFormat("dd/MM/yyyy");
        tvDay.setText("Hôm nay: "+formatDate.format((currentDate)));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtWork.getText().toString().equals("")||edtH.getText().toString().equals("")
                        ||edtM.getText().toString().equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Notes.this);
                    builder.setTitle("Info missing");
                    builder.setIcon(R.drawable.inform);
                    builder.setMessage("Please enter all information of the work!");
                    builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    builder.show();
                }
                else{
                    total = sharedPreferences.getInt("total",0);
                    String str = edtWork.getText().toString()+"  -  "+edtH.getText().toString()+":"
                            +edtM.getText().toString();
                    arr.add(str);
                    arrAdap.notifyDataSetChanged();
                    editor.putString("data"+total,str);
                    total++;
                    editor.putInt("total",total);
                    editor.commit();
                    edtH.setText("");
                    edtM.setText("");
                    edtWork.setText("");
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Notes.this);
                builder.setTitle("Alert");
                builder.setMessage("Are you sure you to permanently delete this line?");
                builder.setIcon(R.drawable.inform);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String tmp = arr.get(position);
                        arr.remove(position);
                        arrAdap.notifyDataSetChanged();
                        int i=0;
                        while(i<=total){
                            if(sharedPreferences.getString("data"+i,"")==tmp) {
                                editor.putString("data" + i, "");
                                editor.commit();
                                break;
                            }
                            i++;
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();

            }
        });
    }
    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
        editor.commit();
    }
}