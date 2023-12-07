package com.example.tncoffee.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tncoffee.R;

public class ChucNang extends AppCompatActivity {

    Button bntQLNhanViien , btnQLMenu , btnQLKho , btnOrder,btnTTCuaHang , btnHoaDon , btnLogOut, btnMenu,btnThongke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chucnang);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trang Chủ");
        setControls();
        setEvents();
    }
    private void setEvents() {

        bntQLNhanViien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChucNang.this, QLNhanVien.class);
                startActivity(intent);
            }
        });


        btnQLMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChucNang.this, QLMon.class);
                startActivity(intent);
            }
        });
        btnQLKho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChucNang.this, QLKho.class);
                startActivity(intent);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChucNang.this, OrderMon.class);
                startActivity(intent);
            }
        });

        btnTTCuaHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChucNang.this, TTCuaHang.class);
                startActivity(intent);
            }
        });
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChucNang.this, QLHoaDon.class);
                startActivity(intent);
            }
        });

        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChucNang.this, Menu.class);
                startActivity(intent);
            }
        });

//        btnThongke.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(ChucNang.this, ThoongKeDoanhThu.class);
//                startActivity(intent);
//            }
//        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ChucNang.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void setControls() {
        bntQLNhanViien = findViewById(R.id.btnQLNhanVien);
        btnQLMenu = findViewById(R.id.btnQLMenu);
        btnQLKho = findViewById(R.id.btnQLKho);
        btnOrder = findViewById(R.id.btnOrder);
        btnHoaDon = findViewById(R.id.btnHoaDon);
        btnTTCuaHang = findViewById(R.id.btnTTCuaHang);
        btnLogOut = findViewById(R.id.btnLogOut);
        btnMenu = findViewById(R.id.btnMenu);
    }
}