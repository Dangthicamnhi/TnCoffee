package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tncoffee.Adapter.Adapter_Order;
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
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
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