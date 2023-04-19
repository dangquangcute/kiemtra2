package com.example.kiemtra2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Button btn1,btn2;
    EditText edt1,edt2,edt3;
    String ten,matkhau,email;
    int quyen;
    Spinner sp1;
    private static int id = -1;
    public static MyDatabase database;

    public static ArrayList<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        btn1 = findViewById(R.id.btndangky);
        btn2 = findViewById(R.id.btndangnhap);
        edt1 = findViewById(R.id.edtusr1);
        edt2 = findViewById(R.id.edtpw1);

        edt3 = findViewById(R.id.edtemail);
        sp1 = findViewById(R.id.sp);
        database = new MyDatabase(MainActivity2.this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.numbers, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        sp1.setAdapter(adapter);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                them(view);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void them(View view) {
        User user = layDuLieuNguoiDung();
        if (user != null) {
            if (users == null) {
                users = new ArrayList<User>();
            }
            if (database.them(user) != -1) {
                users.add(user);
                // Cập nhật lại danh sách
                edt1.setText(null);
                edt2.setText(null);
                edt3.setText(null);
                id = -1;
            }
        }
    }
    public User layDuLieuNguoiDung() {
        ten = edt1.getText().toString();
        matkhau = edt2.getText().toString();
        email = edt3.getText().toString();
        quyen = Integer.parseInt(sp1.getSelectedItem().toString());
        if (ten.trim().length() == 0
                || matkhau.trim().length() == 0 ||  email.trim().length() == 0)
            return null;
        User donHang = new User();
        donHang.setId(id);
        donHang.setTendangnhap(ten);
        donHang.setMatkhau(matkhau);
        donHang.setEmail(email);
        donHang.setSoquyen(quyen);
        return donHang;
    }
}