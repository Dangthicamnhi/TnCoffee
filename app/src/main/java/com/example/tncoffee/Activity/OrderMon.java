package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tncoffee.Adapter.Adapter_Order;
import com.example.tncoffee.Model.Order;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;

public class OrderMon extends AppCompatActivity {
    TextView tvTongTien;
    ListView lvDanhSachSP_Mua;
    Button btnThanhToan;
    static List<Order> data_Order = new ArrayList<>();
    static Adapter_Order adapter_Order;
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
        adapter_Order = new Adapter_Order(this,R.layout.item_order,data_Order);
        lvDanhSachSP_Mua.setAdapter(adapter_Order);
        tvTongTien.setText(tinhTongTien() + " VNĐ");
    }

    public int tinhTongTien(){
        int tong = 0;

        for(Order item : data_Order){
            tong += item.getThanhTien();
        }
        return tong;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
