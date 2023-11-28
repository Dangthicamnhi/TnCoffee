package com.example.tncoffee.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.tncoffee.R;

public class Order extends AppCompatActivity {
    EditText edtItemName, edtQuantity;
    RadioGroup radgSize;
    Spinner spTenMon;
    Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Order");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Ánh xạ các thành phần trong giao diện XML
        edtItemName = findViewById(R.id.edtItemName);
        edtQuantity = findViewById(R.id.edtQuantity);
        radgSize = findViewById(R.id.radgSize);
        spTenMon = findViewById(R.id.spTenMon);
        btnOrder = findViewById(R.id.btnOrder);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý sự kiện khi nhấn nút "Đặt hàng"
                String itemName = edtItemName.getText().toString();
                String quantity = edtQuantity.getText().toString();
                int selectedSizeId = radgSize.getCheckedRadioButtonId();
                String size = "";
                if (selectedSizeId != -1) {
                    RadioButton selectedSize = findViewById(selectedSizeId);
                    size = selectedSize.getText().toString();
                }
                String selectedMon = spTenMon.getSelectedItem().toString();

                // TODO: Thực hiện các hành động liên quan đến đặt hàng, như gửi dữ liệu lên server, lưu vào CSDL, v.v.

                // Chuyển sang trang thanh toán hóa đơn
                goToPaymentActivity();
            }
        });
    }

    private void goToPaymentActivity() {
        // TODO: Chuyển sang trang thanh toán hóa đơn (PaymentActivity)
    }
}
