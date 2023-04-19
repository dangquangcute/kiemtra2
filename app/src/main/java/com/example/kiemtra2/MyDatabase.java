package com.example.kiemtra2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class MyDatabase {
    SQLiteDatabase database;
    UserMedia helper;
    public MyDatabase(Context context) {
        helper = new UserMedia(context);
        /*
         * Phương thức “getWritableDatabase()” dùng
         * để tạo hoặc mở cơ sở dữ liệu để đọc và
         * ghi vào cơ sở dữ liệu.
         */
        database = helper.getWritableDatabase();
    }
    public Cursor layTatCaDuLieu() {
// Biến cot là khai báo danh sách các cột cần lấy.
        String[] cot = { UserMedia.COT_ID,
                UserMedia.COT_TEN,
                UserMedia.COT_MATKHAU,
                UserMedia.COT_EMAIL,
                UserMedia.COT_QUYEN};
        /*
         * Cursor như là 1 bảng cơ sở dữ liệu được trả ra
         * sau khi truy vấn trong cơ sở dữ liệu.
         */
        Cursor cursor = null;
        cursor = database.query(UserMedia.
                        TEN_BANG_USER, cot, null, null, null, null,
                UserMedia.COT_ID + " DESC");
        return cursor;
    }
    public Cursor layTatCaDuLieu2() {
        String[] cot = { UserMedia.COT_ID, UserMedia.COT_TEN, UserMedia.COT_MATKHAU, UserMedia.COT_EMAIL,UserMedia.COT_QUYEN};
        String whereClause = UserMedia.COT_QUYEN + " >= 10";
        Cursor cursor = database.query(UserMedia.TEN_BANG_USER, cot, whereClause, null, null, null, UserMedia.COT_ID + " DESC");
        return cursor;
    }
    @SuppressLint("Range")
    public ArrayList<User> layTatCaDonHang() {
        ArrayList<User> danhSachDonHang = new ArrayList<User>();
        Cursor cursor = layTatCaDuLieu();
        while(cursor.moveToNext()) {
            User donHang = new User();
            donHang.setId(cursor.getInt(cursor.getColumnIndex(UserMedia.COT_ID)));
            donHang.setTendangnhap(cursor.getString(cursor.getColumnIndex(UserMedia.COT_TEN)));
            donHang.setMatkhau(cursor.getString(cursor.getColumnIndex(UserMedia.COT_MATKHAU)));
            donHang.setEmail(cursor.getString(cursor.getColumnIndex(UserMedia.COT_EMAIL)));
            donHang.setSoquyen(cursor.getInt(cursor.getColumnIndex(UserMedia.COT_QUYEN)));
            danhSachDonHang.add(donHang);
        }
        cursor.close();
        return danhSachDonHang;
    }

    public long them(User donHang) {
        /*
         * ContentValues là đối tượng lưu trữ dữ liệu, và
         * SQLiteDatabase sẽ nhận dữ liệu thông qua đối tượng
         * này để thực hiện các câu lệnh truy vấn.
         */
        ContentValues values = new ContentValues();
        values.put(UserMedia.COT_TEN,
                donHang.getTendangnhap());
        values.put(UserMedia.COT_MATKHAU,
                donHang.getMatkhau());
        values.put(UserMedia.COT_EMAIL,
                donHang.getEmail());
        values.put(UserMedia.COT_QUYEN,
                donHang.getSoquyen());
        /*
         * Thêm vào cơ sở dữ liệu cần 2 đối số chính là
         * Tên Bảng và dữ liệu cần thêm.
         */
        return database.insert(UserMedia.
                TEN_BANG_USER, null, values);
    }
}
