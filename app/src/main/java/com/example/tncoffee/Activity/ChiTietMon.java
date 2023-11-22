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

import com.example.tncoffee.Model.NhanVien;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChiTietMon extends AppCompatActivity {
    EditText edtMaMon, edtTenMon, edtGia;
    TextView tvMaMon;
    Spinner spMon;
    ImageView ivHinh;
    Button btnThem, btnXoa, btnSua, btnLamMoi;
    ListView lvView;
    List<String> danhsachSP = new ArrayList<>();
    ArrayAdapter adapter_LSP;
    List<SanPham> danhSach = new ArrayList<>();
    int index = -1 ;
    int lengthListSP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chi Tiết Món");
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

        tvMaMon = findViewById(R.id.tvMaMon);
        edtMaMon = findViewById(R.id.edtMaMon);
        edtTenMon = findViewById(R.id.edtTenMon);
        edtGia = findViewById(R.id.edtGiaMon);
        spMon = findViewById(R.id.spMon);
        ivHinh = findViewById(R.id.ivAnh);
        btnThem = findViewById(R.id.btnThemMon);
        btnXoa = findViewById(R.id.btnXoaMon);
        btnSua = findViewById(R.id.btnSuaMon);
        btnLamMoi = findViewById(R.id.bntLamMoiMon);
        lvView = findViewById(R.id.lvDanhSachMon);
    }

    private void setEvents() {
        KhoiTao();
        adapter_LSP = new ArrayAdapter(this, android.R.layout.simple_list_item_1, danhsachSP);
        spMon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spMon.getSelectedItem().equals("Đồ Ăn"))
                    ivHinh.setImageResource(R.drawable.ic_doan);
                if (spMon.getSelectedItem().equals("Nước Uống"))
                    ivHinh.setImageResource(R.drawable.ic_nuocuong);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spMon.setAdapter(adapter_LSP);
        SanPham sp = (SanPham) getIntent().getSerializableExtra("item");
        edtTenMon.setText(sp.getTen());
        edtGia.setText(sp.getGia());
        if (sp.getLoai().equals("Đồ Ăn")) {
            spMon.setSelection(0);
        }
        if (sp.getLoai().equals("Nước Uống")) {
            spMon.setSelection(1);
        }
        lengthListSP = (int) getIntent().getSerializableExtra("lengthListSanPham");
        boolean isCreateNew = (boolean) getIntent().getSerializableExtra("isCreateNew");

        if (isCreateNew) {
            btnXoa.setVisibility(View.INVISIBLE);
            btnSua.setVisibility(View.INVISIBLE);
            String uniqueID = UUID.randomUUID().toString();
            edtMaMon.setText("ID: " + uniqueID);
            edtTenMon.setText(sp.getTen());
            edtGia.setText(sp.getGia());
        } else {
            btnThem.setVisibility(View.INVISIBLE);
            btnLamMoi.setVisibility(View.INVISIBLE);
            edtMaMon.setText("ID" + sp.getMa());
            edtTenMon.setText(sp.getTen());
            edtGia.setText(sp.getGia());
        }

        btnLamMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uniqueID = UUID.randomUUID().toString();
                edtMaMon.setText("ID: " + uniqueID);
                edtTenMon.setText("");
                edtGia.setText("");
                spMon.setSelection(0);

            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ma = edtMaMon.getText().toString();
                String ten = edtTenMon.getText().toString();
                String gia = edtGia.getText().toString();
                String selectedItem = spMon.getSelectedItem().toString();
                // Kiểm tra thông tin bắt buộc đã được nhập
                if (ma.isEmpty() || ten.isEmpty() || gia.isEmpty() || selectedItem.isEmpty()) {
                    Toast.makeText(ChiTietMon.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                    return;
                }
                QLMon .danhSach.add(new SanPham(ma, ten, gia, selectedItem));

                QLMon.adap.notifyDataSetChanged();

                onBackPressed();
                Toast.makeText(ChiTietMon.this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ChiTietMon.this, QLMon.class);
                startActivity(intent);
            }
        });


        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (SanPham item : QLMon.danhSach) {
                    if (item.getMa().equals(edtMaMon.getText().toString())) {
                        SanPham.GiamSoLuong(item.getLoai());
                        QLMon.danhSach.remove(item);
                        break;
                    }
                }
                QLMon.adap.notifyDataSetChanged();
                onBackPressed();
                Toast.makeText(ChiTietMon.this, "Xóa nhân viên thành công", Toast.LENGTH_SHORT).show();
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hasChanges = false;

                for (SanPham item : QLMon.danhSach) {
                    if (item.getMa().equals(edtMaMon.getText().toString())) {
                        // Kiểm tra xem thông tin đã thay đổi hay chưa
                        String newTen = edtTenMon.getText().toString();
                        String newGia = edtGia.getText().toString();
                        String newLoai = spMon.getSelectedItem().toString();

                        if (!item.getTen().equals(newTen) || !item.getGia().equals(newGia) || !item.getLoai().equals(newLoai)) {
                            item.setTen(newTen);
                            item.setGia(newGia);
                            item.setLoai(newLoai);
                            hasChanges = true;
                            break;
                        }
                    }
                }

                if (hasChanges) {
                    QLMon.adap.notifyDataSetChanged();
                    Toast.makeText(ChiTietMon.this, "Sửa nhân viên thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ChiTietMon.this, "Không có sự thay đổi thông tin", Toast.LENGTH_SHORT).show();
                }

                onBackPressed();
            }
        });

//        btnSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                boolean hasChanges = false;
//
//                for (SanPham item : QLMon.danhSach) {
//                    if (item.getMa().equals(edtMaMon.getText().toString())) {
//                        // Kiểm tra xem thông tin đã thay đổi hay chưa
//                        if (!item.getTen().equals(edtTenMon.getText().toString()) ||
//                                !item.getGia().equals(edtGia.getText().toString()) ||
//                                !item.getLoai().equals(spMon.getSelectedItem().toString())) {
//                            item.setTen(edtTenMon.getText().toString());
//                            item.setGia(edtGia.getText().toString());
//                            item.setLoai(spMon.getSelectedItem().toString());
//                            hasChanges = true;
//                            break; // Dừng vòng lặp sau khi tìm thấy nhân viên
//                        }
//                    }
//                }
//
//                if (hasChanges) {
//                    QLMon.adap.notifyDataSetChanged();
//                    Toast.makeText(ChiTietMon.this, "Sửa nhân viên thành công", Toast.LENGTH_SHORT).show();
//
//
//                } else {
//                    Toast.makeText(ChiTietMon.this, "Không có sự thay đổi thông tin", Toast.LENGTH_SHORT).show();
//                }
//
//                onBackPressed();
//            }
//        });

    }


    private void KhoiTao() {
        danhsachSP.add("Đồ ăn");
        danhsachSP.add("Nước Uống");

    }
}