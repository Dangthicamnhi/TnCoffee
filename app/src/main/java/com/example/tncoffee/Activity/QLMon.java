package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.tncoffee.Adapter.Custom_Adapter_SanPham;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;

public class QLMon extends AppCompatActivity {

    ListView lvDanhSach;
    TextView tvThongKe;
    Button btnChiTiet , btnThongKe;
    static List<SanPham> danhSach = new ArrayList<>();
    static Custom_Adapter_SanPham adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlmon);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản Lý Món");
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setControl() {
        lvDanhSach = findViewById(R.id.lvDanhSachMon);
        btnChiTiet = findViewById(R.id.btnChiTietMon);
        tvThongKe = findViewById(R.id.tvThongKeMon);
        btnThongKe = findViewById(R.id.btnThongKeMon);
    }

    private void setEvent() {
        adap = new Custom_Adapter_SanPham(this, R.layout.item_sanpham, danhSach);
        lvDanhSach.setAdapter(adap);

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QLMon.this, ChiTietMon.class);
                intent.putExtra("item" ,danhSach.get(i));
                intent.putExtra("lengthListSanPham" , danhSach.size());
                intent.putExtra("isCreateNew" , false);
                startActivity(intent);
            }
        });
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QLMon.this, ChiTietMon.class);
                intent.putExtra("item" ,new SanPham("", "", "" , "" ));
                intent.putExtra("lengthListSanPham" , danhSach.size());
                intent.putExtra("isCreateNew" , true);
                startActivity(intent);
            }
        });
        //Nút thống kê số lượng Nhân viên
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvThongKe.setText(SanPham.ThongKe(danhSach));
            }
        });
    }
}