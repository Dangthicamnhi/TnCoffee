package com.example.tncoffee.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tncoffee.Adapter.Adapter_HoaDon;
import com.example.tncoffee.Database.DBHoaDon;
import com.example.tncoffee.Model.HoaDon;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;

public class QLHoaDon extends AppCompatActivity {

    ListView lvDanhSachHoaDon;
    static Adapter_HoaDon adapter_HoaDon;
    DBHoaDon dbHoaDon;
    static List<HoaDon> data_hoadon = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHoaDon = new DBHoaDon(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlhoa_don);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sach hoa don");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setEvent() {
        if (data_hoadon != null) {
            adapter_HoaDon = new Adapter_HoaDon(this, R.layout.item_hoadon,data_hoadon);
            lvDanhSachHoaDon.setAdapter(adapter_HoaDon);
        }
        lvDanhSachHoaDon.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (data_hoadon != null) {
                    if (data_hoadon.size() > 0) {
                        Intent intent = new Intent(QLHoaDon.this, Payment.class);
                        intent.putExtra("item", data_hoadon.get(position));
                        startActivity(intent);
                    } else {
                        Toast.makeText(QLHoaDon.this, "Danh sách rỗng", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        docDuLieu();
    }

    private void docDuLieu() {
        data_hoadon.clear();
        data_hoadon.addAll(dbHoaDon.DocDL(2));
    }


    private void setControl() {
        lvDanhSachHoaDon = findViewById(R.id.lvDanhSachHoaDon);
    }
}

