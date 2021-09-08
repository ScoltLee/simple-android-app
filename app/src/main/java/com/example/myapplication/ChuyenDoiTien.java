package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.text.DecimalFormat;

public class ChuyenDoiTien extends AppCompatActivity {
    RadioButton rbusd, rbeur, rbjpy;
    EditText edtvn, edtnt;
    Button btnnt, btnvn, btnclear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyen_doi_tien);
        rbusd = findViewById(R.id.rbusd);
        rbeur = findViewById(R.id.rbeur);
        rbjpy = findViewById(R.id.rbjpy);
        edtvn = findViewById(R.id.edtvn);
        edtnt = findViewById(R.id.edtnt);
        btnnt = findViewById(R.id.btnnt);
        btnclear = findViewById(R.id.btnclear);
        btnvn = findViewById(R.id.btnvn);

        btnclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtvn.setText("");
                edtnt.setText("");
                edtvn.requestFocus();
            }
        });

        btnnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double vnd = Double.parseDouble(edtvn.getText()+"");
                DecimalFormat dcf = new DecimalFormat("#.0");
                if(rbusd.isChecked())
                    edtnt.setText(dcf.format(vnd/22280)+"");
                else
                if(rbeur.isChecked())
                    edtnt.setText(dcf.format(vnd/24280)+"");
                else
                    edtnt.setText(dcf.format(vnd/204)+"");
            }
        });

        btnvn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double nt = Double.parseDouble(edtnt.getText()+"");
                DecimalFormat dcf = new DecimalFormat("#.0");
                if(rbusd.isChecked())
                    edtvn.setText(dcf.format(nt*22280)+"");
                else
                if(rbeur.isChecked())
                    edtvn.setText(dcf.format(nt*24280)+"");
                else
                    edtvn.setText(dcf.format(nt*204)+"");
            }
        });

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder b =new AlertDialog.Builder(ChuyenDoiTien.this);
        b.setTitle("Question");
        b.setMessage("Are you sure you want to exit?");
        b.setIcon(R.drawable.inform);
        b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                finish();
            }
        });
        b.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                dialog.cancel();
            }
        });
        b.create().show();
    }
}