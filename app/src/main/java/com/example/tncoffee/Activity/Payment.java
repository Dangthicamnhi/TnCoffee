package com.example.tncoffee.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.tncoffee.R;

public class Payment extends AppCompatActivity {
   TextView tvItemName, tvQuantity, tvSize, tvSelectedMon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Hóa Đơn");
        actionBar.setDisplayHomeAsUpEnabled(true);

            // Ánh xạ các thành phần trong giao diện XML
            tvQuantity = findViewById(R.id.tvQuantity);
            tvSize = findViewById(R.id.tvSize);
            tvSelectedMon = findViewById(R.id.tvSelectedMon);

            // Nhận dữ liệu từ trang Order
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                String itemName = extras.getString("itemName");
                String quantity = extras.getString("quantity");
                String size = extras.getString("size");
                String selectedMon = extras.getString("selectedMon");

                // Hiển thị dữ liệu lên giao diện trang thanh toán hóa đơn
                tvItemName.setText(itemName);
                tvQuantity.setText(quantity);
                tvSize.setText(size);
                tvSelectedMon.setText(selectedMon);
            }
        }
}