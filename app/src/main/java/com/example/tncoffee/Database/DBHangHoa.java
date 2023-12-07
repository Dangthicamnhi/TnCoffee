package com.example.tncoffee.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tncoffee.Model.HangHoa;
import com.example.tncoffee.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class DBHangHoa extends SQLiteOpenHelper {

    /**
     * Khởi tạo DBSANPHAM
     */
    public DBHangHoa(@Nullable Context context) {
        // Gọi hàm khởi tạo của lớp cha SQLiteOpenHelper
        super(context, "dbQuanLyBanHang.HangHoa", null, 1);
    }

    /**
     * Tạo bảng tbHangHoa
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

        // Thực hiện câu lệnh SQL để tạo bảng
        db.execSQL("Create Table If not exists HangHoa (ma INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , ten text, gia text, soluong text, loaisp text)");

        // Thực hiện câu lệnh SQL để tạo bảng
        db.execSQL("Create Table If not exists SanPham (ma INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , ten text, gia text, loaisp text)");
    }

    /**
     * Thêm hàng hóa
     *
     * @param o Hàng hóa
     */
    public void ThemDLTrongKho(HangHoa o) {
        // Câu lệnh SQL để thêm một dòng vào bảng HangHoa
        String sql = "Insert into HangHoa(ten, gia, soluong, loaisp) values(?,?,?,?)";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng HangHoa
        db.execSQL(sql, new String[]{o.getTen(), o.getGia(), o.getSoLuong(), o.getLoai()});
    }

    /**
     * Thêm sản phẩm trên menu
     *
     * @param o Hàng hóa
     */
    public void ThemDLTrenMenu(SanPham o) {
        // Câu lệnh SQL để thêm một dòng vào bảng HangHoa
        String sql = "Insert into SanPham(ten, gia, loaisp) values(?,?,?)";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng HangHoa
        db.execSQL(sql, new String[]{o.getTen(), o.getGia(), o.getLoai()});
    }

    /**
     * Xóa hàng hóa trong kho
     *
     * @param o hanghoa
     */
    public void XoaDLTrongKho(HangHoa o) {
        // Câu lệnh SQL để xóa một dòng từ bảng HangHoa dựa trên mã hàng hóa
        String sql = "Delete from HangHoa where ma = ?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với mã hàng hóa từ đối tượng HangHoa
        db.execSQL(sql, new String[]{o.getMa() + ""});
    }

    /**
     * Xóa hàng hóa trên menu
     *
     * @param o hanghoa
     */
    public void XoaDLTrenMenu(SanPham o) {
        // Câu lệnh SQL để xóa một dòng từ bảng HangHoa dựa trên mã hàng hóa
        String sql = "Delete from SanPham where ma = ?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với mã hàng hóa từ đối tượng HangHoa
        db.execSQL(sql, new String[]{o.getMa() + ""});
    }

    /**
     * Sửa thông tin hàng hóa trong kho
     *
     * @param o The hanghoa.
     */
    public void SuaDLTrongKho(HangHoa o) {
        // Câu lệnh SQL để cập nhật thông tin hàng hóa trong bảng HangHoa
        String sql = "Update HangHoa set ten = ?, gia=?, soluong=?, loaisp=? where ma=?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng HangHoa
        // o.getTen() và o.getGioitinh() sẽ thay thế cho ?, o.getMa() sẽ thay thế cho ?
        db.execSQL(sql, new String[]{o.getTen(), String.valueOf(o.getGia()),
                String.valueOf(o.getSoLuong()), o.getLoai() + "", o.getMa() + ""});
    }

    /**
     * Sửa thông tin sản phẩm trên menu
     *
     * @param o The hanghoa.
     */
    public void SuaDLTrenMenu(SanPham o) {
        // Câu lệnh SQL để cập nhật thông tin hàng hóa trong bảng HangHoa
        String sql = "Update SanPham set ten = ?, gia=?, loaisp=? where ma=?";

        // Mở cơ sở dữ liệu để ghi
        SQLiteDatabase db = getWritableDatabase();

        // Thực hiện câu lệnh SQL với các tham số từ đối tượng HangHoa
        // o.getTen() và o.getGioitinh() sẽ thay thế cho ?, o.getMa() sẽ thay thế cho ?
        db.execSQL(sql, new String[]{o.getTen(), String.valueOf(o.getGia()), o.getLoai() + "", o.getMa() + ""});
    }

    /**
     * Đọc dữ liệu sản phảm trong kho
     *
     * @return danh sách đối tượng hàng hóa
     */
    public List<HangHoa> DocDLHangHoaTrongKho() {
        // Khởi tạo danh sách hàng hóa
        List<HangHoa> listHangHoa = new ArrayList<>();
        // Câu lệnh SQL để lấy tất cả dữ liệu từ bảng HangHoa
        String sql = "Select * from HangHoa";
        // Mở cơ sở dữ liệu để đọc
        SQLiteDatabase db = getReadableDatabase();
        // Thực hiện truy vấn SQL và lưu kết quả vào Cursor
        Cursor cursor = db.rawQuery(sql, null);
        // Kiểm tra xem con trỏ có dữ liệu không
        if (cursor.moveToFirst()) {
            do {
                // Khởi tạo đối tượng hàng hóa mới
                HangHoa hanghoa = new HangHoa();
                // Đọc dữ liệu từ cột 0 (Mã hàng hóa) và cập nhật vào đối tượng
                hanghoa.setMa(cursor.getString(0));

                // Đọc dữ liệu từ cột 1 (Tên hàng hóa) và cập nhật vào đối tượng
                hanghoa.setTen(cursor.getString(1));

                // Đọc dữ liệu từ cột 2 (Giá) và cập nhật vào đối tượng
                hanghoa.setGia(cursor.getString(2));

                // Đọc dữ liệu từ cột 3 (Số lượng) và cập nhật vào đối tượng
                hanghoa.setSoLuong(cursor.getString(3));

                // Đọc dữ liệu từ cột 4 (loại sản phẩm) và cập nhật vào đối tượng
                hanghoa.setLoai(cursor.getString(4));
                // Thêm đối tượng hàng hóa vào danh sách
                listHangHoa.add(hanghoa);
            } while (cursor.moveToNext()); // Di chuyển con trỏ đến hàng tiếp theo
        }
        // Trả về danh sách hàng hóa
        return listHangHoa;
    }

    /**
     * Đọc dữ liệu từ db
     *
     * @return danh sách đối tượng hàng hóa
     */
    public List<SanPham> DocDLSanPhamTrenMenu() {
        // Khởi tạo danh sách hàng hóa
        List<SanPham> listHangHoa = new ArrayList<>();
        // Câu lệnh SQL để lấy tất cả dữ liệu từ bảng HangHoa
        String sql = "Select * from SanPham";
        // Mở cơ sở dữ liệu để đọc
        SQLiteDatabase db = getReadableDatabase();
        // Thực hiện truy vấn SQL và lưu kết quả vào Cursor
        Cursor cursor = db.rawQuery(sql, null);
        // Kiểm tra xem con trỏ có dữ liệu không
        if (cursor.moveToFirst()) {
            do {
                // Khởi tạo đối tượng hàng hóa mới
                HangHoa hanghoa = new HangHoa();
                // Đọc dữ liệu từ cột 0 (Mã hàng hóa) và cập nhật vào đối tượng
                hanghoa.setMa(cursor.getString(0));

                // Đọc dữ liệu từ cột 1 (Tên hàng hóa) và cập nhật vào đối tượng
                hanghoa.setTen(cursor.getString(1));

                // Đọc dữ liệu từ cột 2 (Giá) và cập nhật vào đối tượng
                hanghoa.setGia(cursor.getString(2));

                // Đọc dữ liệu từ cột 4 (loại sản phẩm) và cập nhật vào đối tượng
                hanghoa.setLoai(cursor.getString(3));
                // Thêm đối tượng hàng hóa vào danh sách
                listHangHoa.add(hanghoa);
            } while (cursor.moveToNext()); // Di chuyển con trỏ đến hàng tiếp theo
        }
        // Trả về danh sách hàng hóa
        return listHangHoa;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Xóa bảng
        db.execSQL("DROP TABLE IF EXISTS HangHoa");
        db.execSQL("DROP TABLE IF EXISTS SanPham");
        // Tạo lại bảng
        onCreate(db);
    }
}
