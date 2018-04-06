package com.ngocbich.chitieucanhan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ngoc Bich on 4/6/2018.
 */

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private String name;

    public static final int VERSION = 1;
    public static final String TABLE_NAME = "QLCHITIEU";
    public static final String TABLE_COL_ID = "id";
    public static final String TABLE_COL_CONTENT = "noiDung";
    public static final String TABLE_COL_AMOUNT = "soTien";
    public static final String TABLE_COL_CATEGORY = "loai";
    public static final String TABLE_COL_NOTE = "ghiChu";
    public static final String TABLE_COL_NGAY= "ngay";
    public static final String TABLE_COL_THANG = "thang";
    public static final String TABLE_COL_NAM = "nam";
    public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " ("
            + TABLE_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TABLE_COL_CONTENT + " TEXT, "
            + TABLE_COL_AMOUNT + " INTEGER, "
            + TABLE_COL_CATEGORY + " INTEGER, "
            + TABLE_COL_NOTE + " TEXT, "
            + TABLE_COL_NGAY + " INTEGER, "
            +TABLE_COL_THANG + " INTEGER, "
            +TABLE_COL_NAM + " INTEGER)";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }
}
