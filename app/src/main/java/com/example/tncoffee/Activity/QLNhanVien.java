package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tncoffee.Adapter.Custom_Adapter;
import com.example.tncoffee.Moder.NhanVien;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;

public class QLNhanVien extends AppCompatActivity {
    ListView lvDanhSach;
    TextView tvThongKe;
    Button btnChiTiet , btnThongKe;
    static List<NhanVien> danhSach = new ArrayList<>();
    static Custom_Adapter adap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlnhan_vien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản Lý Nhân Viên");
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }
    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setControl() {

        lvDanhSach = findViewById(R.id.lvDanhSach);
        btnChiTiet = findViewById(R.id.btnChiTietNhanVien);
//        tvThongKe = findViewById(R.id.tvThongKe);
        btnThongKe = findViewById(R.id.btnThongKe);
    }

    private void setEvent() {
        adap = new Custom_Adapter(this, R.layout.item_nhanvien, danhSach);
        lvDanhSach.setAdapter(adap);

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QLNhanVien.this, ChiTietNhanVien.class);
                intent.putExtra("item" ,danhSach.get(i));
                intent.putExtra("lengthListNhanVien" , danhSach.size());
                intent.putExtra("isCreateNew" , false);
                startActivity(intent);
            }
        });
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QLNhanVien.this, ChiTietNhanVien.class);
                intent.putExtra("item" ,new NhanVien("", "", "" , "" , ""));
                intent.putExtra("lengthListNhanVien" , danhSach.size());
                intent.putExtra("isCreateNew" , true);
                startActivity(intent);
            }
        });
//        //Nút thống kê số lượng các loại sản phẩm
//        btnThongKe.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                tvThongKe.setText(NhanVien.ThongKe());
//            }
//        });
    }
    private void KhoiTao() {
//        danhSach.add(new NhanVien("001", "Văn A", "0123456789", "1234567890112","Phòng 1"));
//        danhSach.add(new NhanVien("002", "Văn A" ,"0180954789", "1230946983412", "Phòng 2"));
//        danhSach.add(new NhanVien("003", "Văn A" ,"0094576789", "1234567057579", "Phòng 3"));
    }
}