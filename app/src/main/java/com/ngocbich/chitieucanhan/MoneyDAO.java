package com.ngocbich.chitieucanhan;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ngoc Bich on 4/6/2018.
 */

public class MoneyDAO {
    private static MoneyDAO moneyLogDAO = null;
    private SQLiteDatabase db;
    private DBHelper dbHelper;

    private MoneyDAO(Context context) {
        this.dbHelper = new DBHelper(context, DBHelper.TABLE_NAME, null, DBHelper.VERSION);
        db = dbHelper.getWritableDatabase();
    }

    public static MoneyDAO getInstence(Context context) {
        if (moneyLogDAO == null) {
            moneyLogDAO = new MoneyDAO(context);
        }
        return moneyLogDAO;
    }

    public long insert(ChiTieu... chiTieus) {
        int count = 0;
        ContentValues values = new ContentValues();
        for (ChiTieu moneylog : chiTieus) {
            values.clear();
            values.put(DBHelper.TABLE_COL_CONTENT, moneylog.getNoidung());
            values.put(DBHelper.TABLE_COL_AMOUNT, moneylog.getSoTien());
            values.put(DBHelper.TABLE_COL_CATEGORY, moneylog.getLoai());
            values.put(DBHelper.TABLE_COL_NOTE, moneylog.getGhiChu());
            values.put(DBHelper.TABLE_COL_NGAY, moneylog.getNgay());
            values.put(DBHelper.TABLE_COL_THANG, moneylog.getThang());
            values.put(DBHelper.TABLE_COL_NAM, moneylog.getNam());

            if (db.insert(DBHelper.TABLE_NAME, null, values) != -1) {
                count++;
            }
        }
        return count;
    }

    public int update(ChiTieu... chiTieus) {
        int count = 0;
        ContentValues values = new ContentValues();
        for (ChiTieu moneylog : chiTieus) {
            values.clear();
            values.put(DBHelper.TABLE_COL_CONTENT, moneylog.getNoidung());
            values.put(DBHelper.TABLE_COL_AMOUNT, moneylog.getSoTien());
            values.put(DBHelper.TABLE_COL_CATEGORY, moneylog.getLoai());
            values.put(DBHelper.TABLE_COL_NOTE, moneylog.getGhiChu());
            values.put(DBHelper.TABLE_COL_NGAY, moneylog.getNgay());
            values.put(DBHelper.TABLE_COL_THANG, moneylog.getThang());
            values.put(DBHelper.TABLE_COL_NAM, moneylog.getNam());
            count += db.update(DBHelper.TABLE_NAME, values, DBHelper.TABLE_COL_ID + " =? ",
                    new String[]{moneylog.getId() + ""});
        }
        return count;
    }

    public int delete(ChiTieu chiTieu) {
        return db.delete(DBHelper.TABLE_NAME, DBHelper.TABLE_COL_ID + " =? ", new String[]{chiTieu.getId() + ""});
    }

    public List<ChiTieu> query() {
        Cursor cursor = db.query(DBHelper.TABLE_NAME,
                new String[]{DBHelper.TABLE_COL_ID, DBHelper.TABLE_COL_CONTENT, DBHelper.TABLE_COL_AMOUNT, DBHelper.TABLE_COL_CATEGORY, DBHelper.TABLE_COL_NOTE,DBHelper.TABLE_COL_NGAY,DBHelper.TABLE_COL_THANG,DBHelper.TABLE_COL_NAM},
                null, null, null, null, null);
        List<ChiTieu> moneylogList = new ArrayList<>();

        while (cursor.moveToNext()) {
            moneylogList.add(new ChiTieu(
                    cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_COL_ID)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_COL_CONTENT)),
                    cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_COL_AMOUNT)),
                    cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_COL_CATEGORY)),
                    cursor.getString(cursor.getColumnIndex(DBHelper.TABLE_COL_NOTE)),
                    cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_COL_NGAY)),
                    cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_COL_THANG)),
                    cursor.getInt(cursor.getColumnIndex(DBHelper.TABLE_COL_NAM))
            ));
        }
        return moneylogList;
    }
}
