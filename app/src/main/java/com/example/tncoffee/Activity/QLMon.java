package com.example.tncoffee.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tncoffee.Adapter.Custom_Adapter_SanPham;
import com.example.tncoffee.Database.DBHangHoa;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;

public class QLMon extends AppCompatActivity {
    public static ArrayAdapter<Object> adap;
    ListView lvDanhSachSanPham;//Danh sách sản phẩm trên menu
    TextView tvThongKe;
    Button btnChiTiet, btnThongKe;

    public static List<SanPham> danhSachSanPhamTrenMenu = new ArrayList<>();
    public static Custom_Adapter_SanPham customAdapterSanPham;

    DBHangHoa dbHangHoa;// Db Sản phẩm trên menu

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlmon);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản Lý Món");
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
        lvDanhSachSanPham = findViewById(R.id.lvDanhSachMon);
        btnChiTiet = findViewById(R.id.btnChiTietMon);
        tvThongKe = findViewById(R.id.tvThongKeMon);
        btnThongKe = findViewById(R.id.btnThongKeMon);
    }

    private void setEvent() {
        KhoiTao();

        //Chọn sản phẩm trên danh sách để xem chi tiết
        lvDanhSachSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(QLMon.this, ChiTietMon.class);
                intent.putExtra("item", danhSachSanPhamTrenMenu.get(i));
                intent.putExtra("isCreateNew", false);
                startActivity(intent);
            }
        });

        //Nút Thêm sản phẩm mới
        btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QLMon.this, ChiTietMon.class);
                intent.putExtra("isCreateNew", true);
                startActivity(intent);
            }
        });

        //Nút thống kê số lượng Sản phẩm
        btnThongKe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvThongKe.setText(SanPham.ThongKeSP(danhSachSanPhamTrenMenu));
            }
        });
    }



    private void KhoiTao() {
        danhSachSanPhamTrenMenu.clear();

        dbHangHoa = new DBHangHoa(QLMon.this);

        danhSachSanPhamTrenMenu.addAll(dbHangHoa.DocDLSanPhamTrenMenu());

        customAdapterSanPham = new Custom_Adapter_SanPham(this, R.layout.item_sanpham, danhSachSanPhamTrenMenu);
        lvDanhSachSanPham.setAdapter(customAdapterSanPham);
    }
}