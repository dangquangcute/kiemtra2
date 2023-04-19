package com.example.kiemtra2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edt1,edt2;
    Button btn1,btn2;
    String usrname,password;
    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt1 = findViewById(R.id.edtusr);
        edt2 = findViewById(R.id.edtpw);
        btn1 = findViewById(R.id.btn1);
        myDatabase = new MyDatabase(MainActivity.this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usrname = edt1.getText().toString();
                password = edt2.getText().toString();
                if (checkLogin(usrname, password)) {
                    // Lấy thông tin user từ database
                    User user = getUser(usrname);

                    // Kiểm tra quyền của user
                    if (user.getSoquyen() == 1) {
                        // Quyền đúng, cho phép đăng nhập
                        Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                        intent.putExtra("string","xin chao admin");
                        startActivity(intent);
                    } else {
                        // Quyền sai, thông báo lỗi
                        Toast.makeText(MainActivity.this, "Bạn không có quyền truy cập", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,MainActivity3.class);
                        intent.putExtra("string","xin chao " + user.getTendangnhap());
                    }
                } else {
                    // Tên đăng nhập hoặc mật khẩu sai, thông báo lỗi
                    Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean checkLogin(String username, String password) {
        Cursor cursor = myDatabase.database.rawQuery("SELECT * FROM " + UserMedia.TEN_BANG_USER + " WHERE " +
                UserMedia.COT_TEN + " = ? AND " + UserMedia.COT_MATKHAU + " = ?", new String[]{username, password});
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }
    @SuppressLint("Range")
    private User getUser(String username) {
        Cursor cursor = myDatabase.database.rawQuery("SELECT * FROM " + UserMedia.TEN_BANG_USER + " WHERE " +
                UserMedia.COT_TEN + " = ?", new String[]{username});
        User user = null;
        if (cursor.moveToFirst()) {
            user = new User();
            user.setId(cursor.getInt(cursor.getColumnIndex(UserMedia.COT_ID)));
            user.setTendangnhap(cursor.getString(cursor.getColumnIndex(UserMedia.COT_TEN)));
            user.setMatkhau(cursor.getString(cursor.getColumnIndex(UserMedia.COT_MATKHAU)));
            user.setEmail(cursor.getString(cursor.getColumnIndex(UserMedia.COT_EMAIL)));
            user.setSoquyen(cursor.getInt(cursor.getColumnIndex(UserMedia.COT_QUYEN)));
        }
        cursor.close();
        return user;
    }
}