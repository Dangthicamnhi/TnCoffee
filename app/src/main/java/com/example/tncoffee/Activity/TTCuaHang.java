package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
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

        setControls();
        setEvents();
    }

    private void setEvents() {
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TTCuaHang.this, ChucNang.class);
                startActivity(intent);
            }
        });
    }

    private void setControls() {
        btnExit = findViewById(R.id.btnExit);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}