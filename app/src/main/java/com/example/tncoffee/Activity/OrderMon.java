package com.example.tncoffee.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tncoffee.Adapter.Adapter_Order;
import com.example.tncoffee.Database.ChiTietHoaDon;
import com.example.tncoffee.Database.DBHoaDon;
import com.example.tncoffee.Database.GioHang;
import com.example.tncoffee.Model.Order;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderMon extends AppCompatActivity {
    static TextView tvTongTien;
    ListView lvDanhSachSP_Mua;
    Button btnThanhToan;
    static ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
    static Adapter_Order adapter_Order;
    DBHoaDon dbHoaDon;

    public static List<Order> data_Order = new ArrayList<>();
    public static GioHang giohang = new GioHang();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("OrderMon");
        actionBar.setDisplayHomeAsUpEnabled(true);

        setControls();
        setEvents();
    }

    private void setControls() {
        lvDanhSachSP_Mua = findViewById(R.id.lvDanhSachSP_Mua);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        tvTongTien = findViewById(R.id.tvTongTien);
    }

    private void setEvents() {
        // Khởi tạo database hóa đơn
        dbHoaDon = new DBHoaDon(OrderMon.this);
        // khởi tạo adapter hóa đơn
        adapter_Order = new Adapter_Order(this, R.layout.item_order, data_Order);
        // nạp danh sách hóa đơn vào adapter
        lvDanhSachSP_Mua.setAdapter(adapter_Order);
        // hiển thị tổng tiền
        tvTongTien.setText(tinhTongTien(data_Order) + "");
        btnThanhToan.setOnClickListener(v -> {
            // Khởi tạo một đối tượng Date để lấy thời gian hiện tại
            Date now = new Date();
            // Khởi tạo một đối tượng SimpleDateFormat với định dạng mong muốn
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

            // Định dạng thời gian hiện tại thành chuỗi
            String strDate = simpleDateFormat.format(now);
            // cắt chuỗi
            String[] arrDate = strDate.split("/");

            Date date = parseDate(strDate);


            ChiTietHoaDon chiTietGioHang = taoChiTietGioHang(date);
            dbHoaDon.ThemDL(chiTietGioHang);


            Toast.makeText(OrderMon.this, "Thanh toán thành công", Toast.LENGTH_SHORT).show();
            lamMoiGioHang();

            onBackPressed();
            Intent intent = new Intent(OrderMon.this, ChucNang.class);
            intent.putExtra("isCreateNew", true);

            startActivity(intent);
        });

    }

    /**
     * Nút thanh toán
     *
     * @param data_Order
     * @return
     */
    public static int tinhTongTien(List<Order> data_Order) {
        int tong = 0;

        for (Order item : data_Order) {
            tong += item.getThanhTien();
        }
        return tong;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }


    private Date parseDate(String dateString) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    private ChiTietHoaDon taoChiTietGioHang(Date date) {
        ChiTietHoaDon chiTietGioHang = new ChiTietHoaDon();
        String uniqueID = UUID.randomUUID().toString();
        chiTietGioHang.setTenDH(uniqueID);
        chiTietGioHang.setDataHangHoa(taoHoaDon());
        chiTietGioHang.setNgay(date.getDate());
        chiTietGioHang.setThang(date.getMonth() + 1);
        chiTietGioHang.setNam(date.getYear() + 1900);
        chiTietGioHang.setTongTien((int) Double.parseDouble(tvTongTien.getText().toString()));
        return chiTietGioHang;
    }

    private String taoHoaDon() {
        StringBuilder msg = new StringBuilder();
        for (Map.Entry<SanPham, Integer> entry : giohang.getGiohang().entrySet()) {
            SanPham hangHoa = entry.getKey();
            int quantity = entry.getValue();
            msg.append("Tên sp: ").append(hangHoa.getTen());
            msg.append("\nĐơn giá: ").append(hangHoa.getGia() + "").append(".........................................").append(quantity).append("\n");
        }
        return msg.toString();
    }


    private void lamMoiGioHang() {
        giohang.clear();
//        adapter_Order = new Adapter_Order(OrderMon.this, R.layout.activity_order, data_Order);
//        lvDanhSachSP_Mua.setAdapter(adapter_Order);
        adapter_Order.notifyDataSetChanged();

    }

}
