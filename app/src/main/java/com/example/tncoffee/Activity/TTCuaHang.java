package com.example.tncoffee.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.tncoffee.R;

public class TTCuaHang extends AppCompatActivity {
    Button btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttcua_hang);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Thông Tin Cửa Hàng");
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (btnExit != null) {
            btnExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(TTCuaHang.this, ChucNang.class);
                    startActivity(intent);
                    onBackPressed();
                }
            });
        }

        btnExit = findViewById(R.id.btnExit);
    }
}