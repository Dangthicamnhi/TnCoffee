package com.example.tncoffee.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tncoffee.R;

public class Menu extends AppCompatActivity {

    Button bntQLNhanViien , btnQLNuocUong ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trang Chủ");
        setControls();
        setEvents();
    }
    private void setEvents() {

        bntQLNhanViien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Menu.this, QLNhanVien.class);
                startActivity(intent);
            }
        });
    }

    private void setControls() {
        bntQLNhanViien = findViewById(R.id.btnQLNhanVien);

    }
}