package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tncoffee.Adapter.Adapter_Order;
import com.example.tncoffee.Database.DBHoaDon;
import com.example.tncoffee.Model.HoaDon;
import com.example.tncoffee.Model.Order;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;

public class Payment extends AppCompatActivity {
    EditText edtMaHD, edtNgayXuat, edtThanhTien;
    ListView lvDanhSachSP_Order;
    Button btnHuy, btnExit;
    HoaDon hd;
    List<Order> data_Order = new ArrayList<>();//????????
    Adapter_Order adapter_Order;
    DBHoaDon dbHoaDon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hóa Đơn");
        actionBar.setDisplayHomeAsUpEnabled(true);

        setControl();
        setEvent();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
    private void setEvent() {
        dbHoaDon = new DBHoaDon(this);
//        btnHuy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Nếu có, hiển thị một hộp thoại xác nhận
//                AlertDialog.Builder builder = new AlertDialog.Builder(Payment.this);
//                builder.setMessage("Bạn có muốn xóa hóa đơn này ?");
//                builder.setPositiveButton("Có", (dialog, which) -> {
//                    dbHoaDon.XoaDL(edtMaHD.getText().toString());
//                    dbHoaDon.Xoa_HoaDon(edtMaHD.getText().toString());
//                    dbHoaDon.DocDL(2);
//                    QLHoaDon.adapter_HoaDon.notifyDataSetChanged();
//                    onBackPressed();
//                });
//                builder.setNegativeButton("Không", (dialog, which) -> {
//                    // Nếu người dùng chọn không, đóng hộp thoại
//                    dialog.dismiss();
//                });
//                builder.create().show();
//            }
//        });
//
//        btnExit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(Payment.this, QLHoaDon.class);
//                startActivity(intent);
//            }
//        });


    }

    private void NhanGiaTri() {
        hd = (HoaDon)getIntent().getSerializableExtra("item");
        edtMaHD.setText(hd.getMaHD());
        edtNgayXuat.setText(hd.getNgayXuat());
        edtThanhTien.setText(hd.getThanhTien() + " VNĐ");
        data_Order.addAll(OrderMon.data_Order);
        adapter_Order = new Adapter_Order(this,R.layout.item_order,data_Order);
        lvDanhSachSP_Order.setAdapter(adapter_Order);
    }
    private void setControl() {
        edtThanhTien = findViewById(R.id.edtThanhTien);
        edtMaHD = findViewById(R.id.edtMaHD);
        edtNgayXuat = findViewById(R.id.edtNgayXuat);
        lvDanhSachSP_Order = findViewById(R.id.lvDanhSachHoaDon);
        btnHuy = findViewById(R.id.btnHuy);
        btnExit = findViewById(R.id.btnExit);
    }

}