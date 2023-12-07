package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tncoffee.Database.DoanhThu;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ThoongKeDoanhThu extends AppCompatActivity {
    Spinner spNam;
    TextView tvKetQua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoong_ke_doanh_thu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Trang Chủ");
        setControl();
        setEvent();
    }

    private void setEvent() {
        List<Integer> nam = new ArrayList<>();
        Date d = new Date();
        int namHienTai = Integer.parseInt(DateFormat.format("yyyy", d.getTime()).toString());

        for (int i = namHienTai - 100; i <= namHienTai; i++) {
            nam.add(i);
        }

        ArrayAdapter<Integer> adapter_Nam = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nam);
        adapter_Nam.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spNam.setAdapter(adapter_Nam);
        spNam.setSelection(nam.size() - 1);

        spNam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int selectedYear = nam.get(position);
//                int[] doanhThuTheoNam = DoanhThu.DoanhThuTheoNam(selectedYear);
//                tvKetQua.setText(ThongKe(doanhThuTheoNam));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvKetQua.setText("Chọn năm để xem thống kê doanh thu của năm đó");
            }
        });
    }
    private void setControl() {
        spNam = findViewById(R.id.spNam);
        tvKetQua = findViewById(R.id.tvKetQua);
    }

    private String ThongKe(int[] doanhThu){
        String thongKe = "";
        for(int i = 0; i< 12; i ++){
            thongKe += "Tháng " + String.format("%02d",i+1) + ": " + doanhThu[i] + " VNĐ\n";
        }
        return thongKe;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}