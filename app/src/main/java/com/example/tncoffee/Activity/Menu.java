package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tncoffee.Adapter.Custom_Adapter_SanPham;
import com.example.tncoffee.Database.DBHangHoa;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;

public class Menu extends AppCompatActivity {
    ListView lvDanhSachMon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Menu");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    private void setEvent() {
        // Khởi tạo menu thực đơn, món ăn
        List<SanPham> menuThucDonMonAn = new ArrayList<>();

        // Khởi tạo database thực đơn
        DBHangHoa dbThucDon = new DBHangHoa(Menu.this);

        // Thêm thông tin thực đơn món ăn vào menu
        menuThucDonMonAn.addAll(dbThucDon.DocDLSanPhamTrenMenu());

        // Khởi tạo adapter thực đơn
        Custom_Adapter_SanPham customAdapterSanPham = new Custom_Adapter_SanPham(this, R.layout.item_sanpham, menuThucDonMonAn);

        // Hiển thị thông tin thực đơn
        lvDanhSachMon.setAdapter(customAdapterSanPham);

        // Gắn sự kiện khi nhấp vào thực đơn
        lvDanhSachMon.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(Menu.this, ChiTietOrder.class);
            intent.putExtra("isAdded",true);
            intent.putExtra("item",menuThucDonMonAn.get(position));
            startActivity(intent);
        });
    }

    private void setControl() {
        lvDanhSachMon = findViewById(R.id.lvDanhSachMon);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}