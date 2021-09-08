package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TongHop extends AppCompatActivity {
    EditText edtName, edtCMND, edtBS;
    CheckBox cbDB, cbDS, cbDC;
    Button btnGui;
    RadioGroup group;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tong_hop);
        edtName = findViewById(R.id.edtName);
        edtCMND = findViewById(R.id.edtCMND);
        edtBS = findViewById(R.id.edtBS);
        cbDB = findViewById(R.id.cbDB);
        cbDC = findViewById(R.id.cbDC);
        cbDS = findViewById(R.id.cbDS);
        btnGui = findViewById(R.id.btnGui);
        btnGui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                doShowInformation();
            }
        });
    }
    public void doShowInformation()
    {
        //Kiểm tra tên hợp lệ
        String ten= edtName.getText().toString();
        ten=ten.trim();
        if(ten.length()<3)
        {
            edtName.requestFocus();
            edtName.selectAll();
            Toast.makeText(this, "Tên phải >= 3 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        //kiểm tra CMND hợp lệ
        String cmnd= edtCMND.getText().toString(); cmnd=cmnd.trim();
        if(cmnd.length()!=9)
        {
            edtCMND.requestFocus();
            edtCMND.selectAll();
            Toast.makeText(this, "CMND phải đúng 9 ký tự", Toast.LENGTH_LONG).show();
            return;
        }
        //Kiểm tra bằng cấp
        String bang="";
        group = findViewById(R.id.idgroup);
        int id=group.getCheckedRadioButtonId();
        if(id==-1)
        {

            Toast.makeText(this, "Phải chọn bằng cấp", Toast.LENGTH_LONG).show();
            return;
        }
        RadioButton rad= findViewById(id);
        bang=rad.getText()+"";
        //Kiểm tra sở thích
        String sothich="";
        if(cbDB.isChecked())
            sothich+= cbDB.getText()+"\n";
        if(cbDS.isChecked())
            sothich+= cbDS.getText()+"\n";
        if(cbDC.isChecked())
            sothich+= cbDC.getText()+"\n";
        String bosung= edtBS.getText()+"";
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Thông tin cá nhân");
        builder.setPositiveButton("Đóng", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub
                dialog.cancel();
            }
        });
        //tạo nội dung
        String msg=ten+"\n";
        msg+= cmnd+"\n";
        msg+=bang+"\n";
        msg+=sothich;
        msg+="—————————–\n";
        msg+="Thông tin bổ sung:\n";
        msg+=bosung+ "\n";
        msg+="—————————–";
        builder.setMessage(msg);//thiết lập nội dung
        builder.create().show();//hiển thị Dialog
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder b =new AlertDialog.Builder(TongHop.this);
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