package com.example.tncoffee.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tncoffee.Model.HangHoa;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChiTietKho extends AppCompatActivity {
    EditText edtMaHH, edtTenHH, edtSL, edtGia;
    TextView tvMaHH;
    Spinner spHangHoa;
    ImageView ivHinh;
    Button btnThem, btnXoa, btnSua, btnLamMoi;
    ListView lvView;
    List<String> danhsachHangHoa = new ArrayList<>();
    ArrayAdapter adapter_LHH;
    int index = -1;
    int lengthListHH = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_kho);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chi Tiết Hàng Hóa");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        setControls();
        setEvents();
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

    private void setControls() {

        tvMaHH = findViewById(R.id.tvMaHH);
        edtMaHH = findViewById(R.id.edtMaHH);
        edtTenHH = findViewById(R.id.edtTenHH);
        edtSL = findViewById(R.id.edtSL);
        edtGia = findViewById(R.id.edtGia);
        spHangHoa = findViewById(R.id.spHangHoa);
        ivHinh = findViewById(R.id.ivAnh);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnLamMoi = findViewById(R.id.bntLamMoi);
        lvView = findViewById(R.id.lvDanhSach);
    }

    private void setEvents() {
        KhoiTao();
        adapter_LHH = new ArrayAdapter(this, android.R.layout.simple_list_item_1, danhsachHangHoa);
        spHangHoa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spHangHoa.getSelectedItem().equals("Cafe"))
                    ivHinh.setImageResource(R.drawable.ic_cafe);
                if (spHangHoa.getSelectedItem().equals("Đường"))
                    ivHinh.setImageResource(R.drawable.ic_duong);
                if (spHangHoa.getSelectedItem().equals("Bột Mỳ"))
                    ivHinh.setImageResource(R.drawable.ic_botmy);
                if (spHangHoa.getSelectedItem().equals("Trà"))
                    ivHinh.setImageResource(R.drawable.ic_tra);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spHangHoa.setAdapter(adapter_LHH);
        HangHoa hh = (HangHoa) getIntent().getSerializableExtra("item");
        edtTenHH.setText(hh.getTenHH());
        edtSL.setText(hh.getSoLuong());
        edtGia.setText(hh.getGia());
        if (hh.getLoai().equals("Cafe")) {
            spHangHoa.setSelection(0);
        }
        if (hh.getLoai().equals("Đường")) {
            spHangHoa.setSelection(1);
        }
        if (hh.getLoai().equals("Bột Mỳ")) {
            spHangHoa.setSelection(2);
        }
        if (hh.getLoai().equals("Trà")) {
            spHangHoa.setSelection(3);
        }
        lengthListHH = (int) getIntent().getSerializableExtra("lengthListHangHoa");
        boolean isCreateNew = (boolean) getIntent().getSerializableExtra("isCreateNew");


        if (isCreateNew) {
            btnXoa.setVisibility(View.INVISIBLE);
            btnSua.setVisibility(View.INVISIBLE);
            String uniqueID = UUID.randomUUID().toString();
            edtMaHH.setText("ID: " + uniqueID);
            edtTenHH.setText(hh.getTenHH());
            edtSL.setText(hh.getSoLuong());
            edtGia.setText(hh.getGia());
        } else {
            btnThem.setVisibility(View.INVISIBLE);
            btnLamMoi.setVisibility(View.INVISIBLE);
            edtMaHH.setText(hh.getMaHH());
            edtTenHH.setText(hh.getTenHH());
            edtSL.setText(hh.getSoLuong());
            edtGia.setText(hh.getGia());
        }

        btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uniqueID = UUID.randomUUID().toString();
                edtMaHH.setText("ID: " + uniqueID);
                edtTenHH.setText("");
                edtSL.setText("");
                edtGia.setText("");
                spHangHoa.setSelection(0);

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = edtMaHH.getText().toString();
                String ten = edtTenHH.getText().toString();
                String sl = edtSL.getText().toString();
                String gia = edtGia.getText().toString();
                String selectedItem = spHangHoa.getSelectedItem().toString();
//                Toast.makeText(ChiTietNhanVien.this, selectedItem, Toast.LENGTH_LONG).show();
                // Kiểm tra thông tin bắt buộc đã được nhập
                if (ma.isEmpty() || ten.isEmpty() || sl.isEmpty() || gia.isEmpty()) {
                    Toast.makeText(ChiTietKho.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    return;
                }
                QLKho.danhSach.add(new HangHoa(ma, ten, sl, gia, selectedItem));

                QLKho.adap.notifyDataSetChanged();

                onBackPressed();
                Toast.makeText(ChiTietKho.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChiTietKho.this, QLKho.class);
                startActivity(intent);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (HangHoa item : QLKho.danhSach) {
                    if (item.getMaHH().equals(edtMaHH.getText().toString())) {
//                        HangHoa.GiamSoLuong(item.getLoai());
                        QLKho.danhSach.remove(item);
                        break;
                    }
                }
                QLKho.adap.notifyDataSetChanged();
                onBackPressed();
                Toast.makeText(ChiTietKho.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hasChanges = false;

                for (HangHoa item : QLKho.danhSach) {
                    if (item.getMaHH().equals(edtMaHH.getText().toString())) {
                        // Kiểm tra xem thông tin đã thay đổi hay chưa
                        if (!item.getTenHH().equals(edtTenHH.getText().toString()) ||
                                !item.getSoLuong().equals(edtSL.getText().toString()) ||
                                !item.getGia().equals(edtGia.getText().toString()) ||
                                !item.getLoai().equals(spHangHoa.getSelectedItem().toString())) {
                            item.setTenHH(edtTenHH.getText().toString());
                            item.setSoLuong(edtSL.getText().toString());
                            item.setGia(edtGia.getText().toString());
                            item.setLoai(spHangHoa.getSelectedItem().toString());
                            hasChanges = true;
                            break; // Dừng vòng lặp sau khi tìm thấy hàng hóa
                        }
                    }
                }

                if (hasChanges) {
                    QLKho.adap.notifyDataSetChanged();
                    Toast.makeText(ChiTietKho.this, "Sửa thành công", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(ChiTietKho.this, "Không có sự thay đổi thông tin", Toast.LENGTH_SHORT).show();
                }

                onBackPressed();
            }
        });
}


    private void KhoiTao() {
        danhsachHangHoa.add("Cafe");
        danhsachHangHoa.add("Đường");
        danhsachHangHoa.add("Bột Mỳ");
        danhsachHangHoa.add("Trà");

    }
}