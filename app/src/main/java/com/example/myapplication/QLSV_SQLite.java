package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class QLSV_SQLite extends AppCompatActivity {
    Button btnInsert, btnDelete, btnUpdate, btnQuery;
    EditText edtMaLop, edtTenLop, edtSiSo;
    SQLiteDatabase database = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlsv_sqlite);
        btnInsert = (Button) findViewById(R.id.btnInsert);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnQuery = (Button) findViewById(R.id.btnQuery);
        edtMaLop = findViewById(R.id.edtMaLop);
        edtTenLop = findViewById(R.id.edtTenLop);
        edtSiSo = findViewById(R.id.edtSiSo);

        //	Tạo Database
        database = openOrCreateDatabase("qlsinhvien.db", MODE_PRIVATE, null);
        //	Tạo Table nếu không tồn tại
        try {
            docreattable(); //Gọi hàm Tạo Tabel
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("Error", "Table is exists");
        }

        //	Insert Records to Table Lop
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                doinsertrecordtolop(); // Gọi hàm chèn thêm lớp mới
            }
        });

        //	Delete Record from Table Lop
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                deleterowLop(); // Gọi hàm xóa lớp
            }
        });

        //	Update Record to Table lop
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                doupdaterowlop(); //Gọi hàm update lớp
            }
        });

        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                querytablelop(); // Gọi hàm truy vấn lớp
            }
        });
    }

    //	TẠO TABLE
    private void docreattable () {
        // TODO Auto-generated method stub
        String sql1 = "CREATE TABLE tbllop (malop TEXT primary key,tenlop TEXT, siso INTEGER)";
        database.execSQL(sql1);
    }
    private void doinsertrecordtolop () {
        // TODO Auto-generated method stub
        String malop = edtMaLop.getText().toString();
        String tenlop = edtTenLop.getText().toString();
        String siso = edtSiSo.getText().toString();
        ContentValues values = new ContentValues();
        values.put("malop", malop);
        values.put("tenlop", tenlop);
        values.put("siso", siso);
        String msg = "";
        if (database.insert("tbllop", null, values) == -1) {
            msg = "Failed to insert record";
        } else {
            msg = "insert record is successful";
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    private void deleterowLop () {
        // TODO Auto-generated method stub
        String malop = edtMaLop.getText().toString();
        int d = database.delete("tbllop", "malop =?", new String[]{malop});
        String msg = "";
        if (d == 0) {
            msg = "Delete Row " + malop + " Fail";
        } else {
            msg = "Delete Row " + malop + " Sucessful";
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    private void doupdaterowlop () {
        // TODO Auto-generated method stub
        String malop = edtMaLop.getText().toString();
        String new_tenlop = edtTenLop.getText().toString();
        String siso = edtSiSo.getText().toString();
        ContentValues values = new ContentValues();
        values.put("tenlop", new_tenlop);
        values.put("siso", siso);
        String msg = "";
        int ret = database.update("tbllop", values,
                "malop=?", new String[]{malop});
        if (ret == 0) {
            msg = "Failed to update";
        } else {
            msg = "updating is successful";
        }
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    public void querytablelop () {
        Cursor c = database.query("tbllop", null, null, null,
                null, null, null);
        c.moveToFirst();
        String data = "";
        while (c.isAfterLast() == false) {
            data += c.getString(0) + " - " +
                    c.getString(1) + " - " + c.getString(2)+"\n";
            c.moveToNext();
        }
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
        c.close();
    }
}