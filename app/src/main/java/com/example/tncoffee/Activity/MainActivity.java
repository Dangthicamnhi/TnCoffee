package com.example.tncoffee.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tncoffee.R;

public class MainActivity extends AppCompatActivity {

    Button btnDangNhap ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");

        setControl();
        setEvent();

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,Menu.class);
                startActivity(i);
            }
        });


    }

    private void setEvent() {
    }

    private void setControl() {
        btnDangNhap = findViewById(R.id.btnDangNhap);

    }
}