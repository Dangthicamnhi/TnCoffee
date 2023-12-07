package com.example.tncoffee.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tncoffee.Model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class DBHoaDon extends SQLiteOpenHelper {
    public DBHoaDon(@Nullable Context context) {
        super(context, "QL_GioHang", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table HoaDon(maDH text,thongtin text,gia text, Ngay text,Thang text,Nam text)";
        db.execSQL(sql);
    }

    public void ThemDL(ChiTietHoaDon chiTietGioHang) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "INSERT INTO HoaDon VALUES(?,?,?,?,?,?)";
        db.execSQL(sql, new String[]{chiTietGioHang.getTenDH(), chiTietGioHang.getDataHangHoa(), chiTietGioHang.getNgay() + "", chiTietGioHang.getThang() + "", chiTietGioHang.getNam() + "", chiTietGioHang.getTongTien() + ""});
        db.close();
    }

    public void XoaDL(String ma) {
        String sql = "Delete from HoaDon where maDH = ?";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql, new String[]{ma});
    }

    public List<String> TraCuu() {
        List<String> listHoaDon = new ArrayList<>();
        String sql = "Select * from HoaDon";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                listHoaDon.add((String) cursor.getString(0).toString());
            } while (cursor.moveToNext());
        }
        return listHoaDon;
    }

    public List<HoaDon> DocDL(int i) {
        List<HoaDon> chiTietGioHangs = new ArrayList<>();
        String sql = "Select * from HoaDon";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        // Kiểm tra xem con trỏ có dữ liệu không
        if (cursor.moveToFirst()) {
            do {
                HoaDon chiTietGioHang = new HoaDon();
                chiTietGioHang.setMaHD(cursor.getString(0).toString());
                chiTietGioHang.setNgayXuat(cursor.getString(1).toString());
                chiTietGioHang.setThanhTien(Integer.parseInt(cursor.getString(2).toString()));
                // chiTietGioHang.setTongTien(2);

                chiTietGioHangs.add(chiTietGioHang);
            } while (cursor.moveToNext());
        }
        return chiTietGioHangs;
    }

    public void Xoa_HoaDon(String maHD){
        String sql = "DELETE FROM tbHoaDon WHERE maHD=?";

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.execSQL(sql, new String[]{maHD});
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
