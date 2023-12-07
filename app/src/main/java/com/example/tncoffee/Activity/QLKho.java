package com.example.tncoffee.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tncoffee.Adapter.Custom_Adapter_HangHoa;
import com.example.tncoffee.Database.DBHangHoa;
import com.example.tncoffee.Model.HangHoa;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;

public class QLKho extends AppCompatActivity {
    ListView lvDanhSachHangHoa;// danh sách nguyên vật liệu trong kho
    TextView tvThongKeKho;
    Button btnChiTiet , btnThongKe;
    static List<HangHoa> hangHoas = new ArrayList<>();
    @SuppressLint("StaticFieldLeak")
    static Custom_Adapter_HangHoa customAdapterHangHoa;

    DBHangHoa dbHangHoa;// DB Nguyên vật liệu trong kho
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qlkho);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Quản Lý Kho");
        actionBar.setDisplayHomeAsUpEnabled(true);
        setControl();
        setEvent();
    }

    // this event will enable the back
    // function to the button on press
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);

    }

    private void setControl() {

        lvDanhSachHangHoa = findViewById(R.id.lvDanhSach);
        btnChiTiet = findViewById(R.id.btnChiTietKho);
        tvThongKeKho = findViewById(R.id.tvThongKe);
        btnThongKe = findViewById(R.id.btnThongKe);
    }

    private void setEvent() {
        KhoiTao();
        lvDanhSachHangHoa.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(QLKho.this, ChiTietKho.class);
            intent.putExtra("item", hangHoas.get(i));
            intent.putExtra("lengthListHangHoa", hangHoas.size());
            intent.putExtra("isCreateNew", false);
            startActivity(intent);
        });
        btnChiTiet.setOnClickListener(view -> {
            Intent intent = new Intent(QLKho.this, ChiTietKho.class);
            intent.putExtra("item" ,new HangHoa("" , "" ,"" ,"" ,""));
            intent.putExtra("lengthListHangHoa" , hangHoas.size());
            intent.putExtra("isCreateNew" , true);
            startActivity(intent);
        });

        //Nút thống kê số lượng
        btnThongKe.setOnClickListener(view -> tvThongKeKho.setText(HangHoa.ThongKeHH(hangHoas)));
    }

    private void KhoiTao() {
        hangHoas.clear();

        dbHangHoa = new DBHangHoa(QLKho.this);

        hangHoas.addAll(dbHangHoa.DocDLHangHoaTrongKho());

        // cài đặt danh sách hàng hóa
        customAdapterHangHoa = new Custom_Adapter_HangHoa(this, R.layout.item_hanghoa, hangHoas);
        lvDanhSachHangHoa.setAdapter(customAdapterHangHoa);
    }
}