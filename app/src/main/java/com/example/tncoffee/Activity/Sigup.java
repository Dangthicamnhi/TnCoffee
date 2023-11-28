package com.example.tncoffee.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tncoffee.Model.DBDangki;
import com.example.tncoffee.R;

public class Sigup extends AppCompatActivity {
    EditText edtUsername;
    EditText edtPassword;
    EditText edtHoVaTen;
    EditText edtNgaySinh;
    RadioButton rdNam;
    RadioButton rdNu;
    Button btnSignUp;
    Button btnBack;
    DBDangki dbDangKi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sigup);
        setControl();
        setEvent();
    }

    private void setEvent() {
        dbDangKi = new DBDangki(this);

        btnSignUp.setOnClickListener(view -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            String fullName = edtHoVaTen.getText().toString();
            String birthDate = edtNgaySinh.getText().toString();
            String gender = rdNam.isChecked() ? "Nam" : "Nữ";

            dbDangKi.addUser(username, password, fullName, birthDate, gender);

            Toast.makeText(Sigup.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Sigup.this , MainActivity.class) ;
            startActivity(intent);
            onBackPressed();
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sigup.this , MainActivity.class) ;
                startActivity(intent);
                onBackPressed();
            }

        });

    }

    private void setControl() {
        edtUsername = findViewById(R.id.edtuser);
        edtPassword = findViewById(R.id.edtpassword);
        edtHoVaTen = findViewById(R.id.edthoten);
        edtNgaySinh = findViewById(R.id.edtngaysinh);
        rdNam = findViewById(R.id.rdnam);
        rdNu = findViewById(R.id.rdnu);
        btnSignUp = findViewById(R.id.btnDangKi);
        btnBack = findViewById(R.id.btnQuayLai);

    }
}