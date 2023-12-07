package com.example.tncoffee.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DoanhThu extends SQLiteOpenHelper {
    public DoanhThu(@Nullable Context context) {
        super(context, "DoanhThu.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE tbHoaDon (id INTEGER PRIMARY KEY AUTOINCREMENT, ngayLap TEXT, doanhThu INTEGER)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Bạn có thể triển khai phương thức này nếu bạn cần nâng cấp schema của cơ sở dữ liệu.
    }

    public int[] DoanhThuTheoNam(int nam) {
        int[] doanhThu = new int[12];
        for (int i = 0; i < 12; i++) {
            doanhThu[i] = 0;
        }
        String sql = "SELECT * FROM tbHoaDon WHERE ngayLap LIKE '%" + nam + "'";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                String ngayLap = cursor.getString(1);
                int thang = Integer.parseInt(ngayLap.split("/")[1]) - 1;
                doanhThu[thang] += cursor.getInt(2);
            } while (cursor.moveToNext());
        }
        cursor.close();
        sqLiteDatabase.close();
        return doanhThu;
    }
}