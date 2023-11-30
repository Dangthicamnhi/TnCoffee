package com.example.tncoffee.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tncoffee.R;

public class Order extends AppCompatActivity {
     EditText edtItemName;
     EditText edtQuantity;
     RadioGroup radgSize;
     Spinner spTenMon;
     ImageView ivAnh;
     Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Order");
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        setEvents();
        setControls();
    }

    private void setControls() {
        edtItemName = findViewById(R.id.edtItemName);
        edtQuantity = findViewById(R.id.edtQuantity);
        radgSize = findViewById(R.id.radgSize);
        spTenMon = findViewById(R.id.spTenMon);
        ivAnh = findViewById(R.id.ivHinhSanPham);
        btnOrder = findViewById(R.id.btnOrder);
    }

    private void setEvents() {
        // xu lu
       btnOrder.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String itemName = edtItemName.getText().toString();
               String quantity = edtQuantity.getText().toString();
               int selectedSizeId = radgSize.getCheckedRadioButtonId();
               String size = "";

               // Xác định kích thước đã chọn
               if (selectedSizeId == R.id.radbSmall) {
                   size = "S";
               } else if (selectedSizeId == R.id.radbMedium) {
                   size = "M";
               } else if (selectedSizeId == R.id.radbLarge) {
                   size = "L";
               }

               String selectedMenuItem = spTenMon.getSelectedItem().toString();

               // Hiển thị thông báo với các giá trị đã chọn
               String message = "Tên món: " + itemName + "\n"
                       + "Số lượng: " + quantity + "\n"
                       + "Kích thước: " + size + "\n"
                       + "Món đã chọn: " + selectedMenuItem;

               Toast.makeText(Order.this, message, Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(Order.this, Payment.class);
               startActivity(intent);
           }
       });

    }
}
