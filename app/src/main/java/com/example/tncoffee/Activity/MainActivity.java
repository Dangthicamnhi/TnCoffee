package com.example.tncoffee.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tncoffee.Model.DBDangki;
import com.example.tncoffee.R;

public class MainActivity extends AppCompatActivity {

    EditText editTextUsername;
    EditText editTextPassword;
    Button btnlogin;
    Button btnsigup;
    DBDangki dbDangKi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");

        setControl();
        setEvent();

    }

    private void setEvent() {
        dbDangKi = new DBDangki(this);

        btnlogin.setOnClickListener(view -> {
            String username = editTextUsername.getText().toString();
            String password = editTextPassword.getText().toString();
            boolean isLoggedIn = dbDangKi.checkLogin(username, password);
            if (isLoggedIn) {
                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ChucNang.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(MainActivity.this, "Sai tên đăng nhập hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });
        btnsigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Sigup.class);
                startActivity(intent);
            }
        });
    }

    private void setControl() {
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        btnlogin = findViewById(R.id.buttonLogin);
        btnsigup = findViewById(R.id.buttonRegister);
    }
}