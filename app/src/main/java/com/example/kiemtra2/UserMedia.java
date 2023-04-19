package com.example.kiemtra2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserMedia extends SQLiteOpenHelper {
    static final String TEN_DB = "UserMedia";
    static final String TEN_BANG_USER = "User";
    public static final String COT_ID = "_id";
    public static final String COT_TEN = "_ten";
    public static final String COT_MATKHAU = "_matkhau";
    public static final String COT_EMAIL = "_email";
    public static final String COT_QUYEN  = "_quyen";

    private static final String TAO_BANG_USER = ""
            + "create table " + TEN_BANG_USER + " ( "
            + COT_ID + " integer primary key autoincrement ,"
            + COT_TEN + " text not null, "
            + COT_MATKHAU + " text not null,"
            + COT_EMAIL + " text not null,"
            + COT_QUYEN + " integer not null);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TAO_BANG_USER);
    }
    public UserMedia(Context context)
    {
        super(context,TEN_DB,null,1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

}
