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

import com.example.tncoffee.Moder.NhanVien;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChiTietNhanVien extends AppCompatActivity {

    EditText edtTenNV, edtMaNV, edtSDTH, edtCCCD;
    TextView tvMaNV;
    Spinner spNhanVien;
    ImageView ivHinh;
    Button btnThem, btnXoa, btnSua, btnThoat, btnLamMoi;
    ListView lvView;
    List<String> danhsachNhanVien = new ArrayList<>();
    ArrayAdapter adapter_LSP;
    List<NhanVien> danhSach = new ArrayList<>();
    int index = -1;

    int lengthListNV = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_nhan_vien);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chi Tiết Nhân Viên");
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

        tvMaNV = findViewById(R.id.tvMaNV);
        edtMaNV = findViewById(R.id.edtMaNV);
        edtTenNV = findViewById(R.id.edtTenNV);
        edtSDTH = findViewById(R.id.edtSDTH);
        edtCCCD = findViewById(R.id.edtCCCD);
        spNhanVien = findViewById(R.id.spNhanVien);
        ivHinh = findViewById(R.id.ivAnh);
        btnThem = findViewById(R.id.btnThem);
        btnXoa = findViewById(R.id.btnXoa);
        btnSua = findViewById(R.id.btnSua);
        btnLamMoi = findViewById(R.id.bntLamMoi);
        lvView = findViewById(R.id.lvDanhSach);
    }

    private void setEvents() {
        KhoiTao();
        adapter_LSP = new ArrayAdapter(this, android.R.layout.simple_list_item_1, danhsachNhanVien);
        spNhanVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spNhanVien.getSelectedItem().equals("Phòng 1"))
                    ivHinh.setImageResource(R.drawable.ic_nv1);
                if (spNhanVien.getSelectedItem().equals("Phòng 2"))
                    ivHinh.setImageResource(R.drawable.ic_nv2);
                if (spNhanVien.getSelectedItem().equals("Phòng 3"))
                    ivHinh.setImageResource(R.drawable.ic_nv3);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spNhanVien.setAdapter(adapter_LSP);
        NhanVien nv = (NhanVien) getIntent().getSerializableExtra("item");
        edtTenNV.setText(nv.getTenNV());
        edtSDTH.setText(nv.getSDTH());
        edtCCCD.setText(nv.getCCCD());
        if (nv.getPhong().equals("Phòng 1")) {
            spNhanVien.setSelection(0);
        }
        if (nv.getPhong().equals("Phòng 2")) {
            spNhanVien.setSelection(1);
        }
        if (nv.getPhong().equals("Phòng 3")) {
            spNhanVien.setSelection(2);
        }
        lengthListNV = (int) getIntent().getSerializableExtra("lengthListNhanVien");
        boolean isCreateNew = (boolean) getIntent().getSerializableExtra("isCreateNew");

        if (isCreateNew) {
            btnXoa.setVisibility(View.INVISIBLE);
            btnSua.setVisibility(View.INVISIBLE);
            String uniqueID = UUID.randomUUID().toString();
            edtMaNV.setText("ID: " + uniqueID);
            edtTenNV.setText(nv.getTenNV());
            edtSDTH.setText(nv.getSDTH());
            edtCCCD.setText(nv.getCCCD());
        } else {
            btnThem.setVisibility(View.INVISIBLE);
            edtMaNV.setText("ID" + nv.getMaNV());
            edtTenNV.setText(nv.getTenNV());
            edtSDTH.setText(nv.getSDTH());
            edtCCCD.setText(nv.getCCCD());
        }

//         Log.d("lengthListNV", String.valueOf(lengthListNV));
//        tvMaNV.setText(nv.getMaNV());

        btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uniqueID = UUID.randomUUID().toString();
                edtMaNV.setText("ID: " + uniqueID);
                edtTenNV.setText("");
                edtSDTH.setText("");
                edtCCCD.setText("");
                spNhanVien.setSelection(0);

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String maNV = edtMaNV.getText().toString();
                String tenNV = edtTenNV.getText().toString();
                String sdtH = edtSDTH.getText().toString();
                String cccd = edtCCCD.getText().toString();
                String selectedItem = spNhanVien.getSelectedItem().toString();
                Toast.makeText(ChiTietNhanVien.this, selectedItem, Toast.LENGTH_LONG).show();
                // Kiểm tra thông tin bắt buộc đã được nhập
                if (maNV.isEmpty() || tenNV.isEmpty() || sdtH.isEmpty() || cccd.isEmpty()) {
                    Toast.makeText(ChiTietNhanVien.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    return;
                }
                // Kiểm tra số điện thoại
                if (sdtH.length() != 10) {
                    Toast.makeText(ChiTietNhanVien.this, "Số điện thoại phải có 10 số. Vui lòng nhập lại.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Kiểm tra CCCD
                if (cccd.length() != 12) {
                    Toast.makeText(ChiTietNhanVien.this, "CCCD phải có 12 số. Vui lòng nhập lại.", Toast.LENGTH_SHORT).show();
                    return;
                }

                QLNhanVien.danhSach.add(new NhanVien(maNV, tenNV, sdtH, cccd, selectedItem));
                QLNhanVien.adap.notifyDataSetChanged();

                onBackPressed();
                Toast.makeText(ChiTietNhanVien.this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChiTietNhanVien.this, QLNhanVien.class);
                startActivity(intent);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (NhanVien item : QLNhanVien.danhSach) {
                    if (item.getMaNV().equals(edtMaNV.getText().toString())) {
                        QLNhanVien.danhSach.remove(item);
                        break;
                    }
                }
                QLNhanVien.adap.notifyDataSetChanged();
                onBackPressed();
                Toast.makeText(ChiTietNhanVien.this, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
            }
        });


        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean Ktr = false; // Biến item để kiểm tra xem có sự thay đổi thông tin hay không
                for (NhanVien item : QLNhanVien.danhSach) {
                    if (item.getMaNV().equals(edtMaNV.getText().toString())) {
                        String newMaNV = edtMaNV.getText().toString();
                        String newTenNV = edtTenNV.getText().toString();
                        String newSDT = edtSDTH.getText().toString();
                        String newCCCD = edtCCCD.getText().toString();
                        String newPhong = spNhanVien.getSelectedItem().toString();

                        // Kiểm tra xem thông tin đã thay đổi hay chưa
                        if (!item.getMaNV().equals(newMaNV) || !item.getTenNV().equals(newTenNV) || !item.getSDTH().equals(newSDT)
                                || !item.getCCCD().equals(newCCCD) || !item.getPhong().equals(newPhong)) {
                            item.setMaNV(newMaNV);
                            item.setTenNV(newTenNV);
                            item.setSDTH(newSDT);
                            item.setCCCD(newCCCD);
                            item.setPhong(newPhong);
                            Ktr = true; // Đánh dấu là đã có sự thay đổi thông tin
                            break;
                        }
                    }
                }
                if (Ktr) {
                    QLNhanVien.adap.notifyDataSetChanged();
                    Toast.makeText(ChiTietNhanVien.this, "Sửa nhân viên thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChiTietNhanVien.this, "Không có sự thay đổi thông tin", Toast.LENGTH_SHORT).show();
                }

                QLNhanVien.adap.notifyDataSetChanged();
                onBackPressed();
            }

        });
//        btnThoat.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
    }

    private boolean KiemTraTonTai(String ma) {
        for (int i = 0; i < danhSach.size(); i++) {
            if (danhSach.get(i).getMaNV().equals(ma)) {
                return true;
            }
        }
        return false;
    }


    private void KhoiTao() {
        danhsachNhanVien.add("Phòng 1");
        danhsachNhanVien.add("Phòng 2");
        danhsachNhanVien.add("Phòng 3");

    }


}