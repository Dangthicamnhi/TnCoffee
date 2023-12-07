package com.example.tncoffee.Activity;

import android.annotation.SuppressLint;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tncoffee.Database.DBHangHoa;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.util.ArrayList;
import java.util.List;

public class ChiTietMon extends AppCompatActivity {
    EditText edtMaMon, edtTenMon, edtGia;
    TextView tvMaMon;
    Spinner spMon;
    ImageView ivHinh;
    Button btnThem, btnXoa, btnSua, btnLamMoi;
    ListView lvView;
    List<String> danhsachSP = new ArrayList<>();
    ArrayAdapter<String> adapter_LSP;

    DBHangHoa dbHangHoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_mon);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Chi Tiết Món");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        setControls();
        setEvents();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void setControls() {

        tvMaMon = findViewById(R.id.tvMaMon);
        edtMaMon = findViewById(R.id.edtMaMon);
        edtTenMon = findViewById(R.id.edtTenMon);
        edtGia = findViewById(R.id.edtGiaMon);
        spMon = findViewById(R.id.spMon);
        ivHinh = findViewById(R.id.ivHinhSanPham);
        btnThem = findViewById(R.id.btnThemMon);
        btnXoa = findViewById(R.id.btnXoaMon);
        btnSua = findViewById(R.id.btnSuaMon);
        btnLamMoi = findViewById(R.id.bntLamMoiMon);
        lvView = findViewById(R.id.lvDanhSachMon);
    }

    @SuppressLint("SetTextI18n")
    private void setEvents() {
        KhoiTao();

        //Gán adapter vào spinner loại sản phẩm
        adapter_LSP = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, danhsachSP);
        spMon.setAdapter(adapter_LSP);

        //Chọn item trên spinner
        spMon.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spMon.getSelectedItem().toString().equals("Đồ Ăn")) {
                    ivHinh.setImageResource(R.drawable.ic_doan);
                } else if (spMon.getSelectedItem().toString().equals("Nước Uống")) {
                    ivHinh.setImageResource(R.drawable.ic_nuocuong);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                ivHinh.setImageResource(R.drawable.ic_doan);
            }
        });


        boolean isCreateNew = (boolean) getIntent().getSerializableExtra("isCreateNew");

        if (isCreateNew) {
            btnXoa.setVisibility(View.INVISIBLE);
            btnSua.setVisibility(View.INVISIBLE);
//            String uniqueID = UUID.randomUUID().toString();
//            edtMaMon.setText("ID: " + uniqueID);
        } else {
            SanPham sp = (SanPham) getIntent().getSerializableExtra("item");
            assert sp != null;
            edtTenMon.setText(sp.getTen());
            edtGia.setText(sp.getGia());
            // Kiểm tra loại sản phẩm là khong null
            if (sp.getLoai() != null) {
                if (sp.getLoai().equals("Đồ Ăn")) {
                    spMon.setSelection(0);
                }
                if (sp.getLoai().equals("Nước Uống")) {
                    spMon.setSelection(1);
                }
            } else {
                // null thì default value
                spMon.setSelection(0);//default đồ ăn
            }
            btnThem.setVisibility(View.INVISIBLE);
            btnLamMoi.setVisibility(View.INVISIBLE);
            edtMaMon.setText(sp.getMa());
            edtTenMon.setText(sp.getTen());
            edtGia.setText(sp.getGia());
        }

        //Các nút của chức năng Thêm sản phẩm
        btnLamMoi.setOnClickListener(view -> {
//            String uniqueID = UUID.randomUUID().toString();
//            edtMaMon.setText("ID: " + uniqueID);

            edtTenMon.setText("");
            edtGia.setText("");
            spMon.setSelection(0);

        });
        btnThem.setOnClickListener(view -> {
            String ma = edtMaMon.getText().toString();
            String ten = edtTenMon.getText().toString();
            String gia = edtGia.getText().toString();
            String selectedItem = spMon.getSelectedItem().toString();
            // Kiểm tra thông tin bắt buộc đã được nhập
            if (ten.isEmpty() || gia.isEmpty() || selectedItem.isEmpty()) {
                Toast.makeText(ChiTietMon.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_LONG).show();
                return;
            }

            //==================================
            // Thêm thông tin database
            //==================================
            dbHangHoa.ThemDLTrenMenu(new SanPham(ma, ten, gia, selectedItem));
            // cập nhật thông tin danh sách món ăn
            QLMon.customAdapterSanPham.notifyDataSetChanged();

            Toast.makeText(ChiTietMon.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            onBackPressed();
            Intent intent = new Intent(ChiTietMon.this, QLMon.class);
            startActivity(intent);
        });

        //Các nút của chức năng Chi tiết sản phẩm
        btnXoa.setOnClickListener(view -> {
            for (SanPham item : QLMon.danhSachSanPhamTrenMenu) {
                if (item.getMa().equals(edtMaMon.getText().toString())) {
                    QLMon.danhSachSanPhamTrenMenu.remove(item);
                    //==================================
                    // Xóa thông tin database
                    //==================================
                    dbHangHoa.XoaDLTrenMenu(item);
                    break;
                }
            }
            QLMon.customAdapterSanPham.notifyDataSetChanged();

            Toast.makeText(ChiTietMon.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
            onBackPressed();
        });
        btnSua.setOnClickListener(view -> {
            boolean hasChanges = false;

            for (SanPham item : QLMon.danhSachSanPhamTrenMenu) {
                if (item.getMa().equals(edtMaMon.getText().toString())) {
                    if (!item.getTen().equals(edtTenMon.getText().toString()) ||
                            !item.getGia().equals(edtGia.getText().toString()) ||
                            !item.getLoai().equals(spMon.getSelectedItem().toString())) {

                        item.setTen(edtTenMon.getText().toString());
                        item.setGia(edtGia.getText().toString());
                        item.setLoai(spMon.getSelectedItem().toString());

                        //==================================
                        // Cập nhật thông tin database
                        //==================================
                        dbHangHoa.SuaDLTrenMenu(item);
                        hasChanges = true;
                        break; // Dừng vòng lặp sau khi tìm thấy
                    }
                }
            }

            if (hasChanges) {
                QLMon.customAdapterSanPham.notifyDataSetChanged();
                Toast.makeText(ChiTietMon.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(ChiTietMon.this, "Không có sự thay đổi thông tin", Toast.LENGTH_SHORT).show();
            }

            onBackPressed();
        });

    }


    private void KhoiTao() {
        danhsachSP.add("Đồ Ăn");
        danhsachSP.add("Nước Uống");
        dbHangHoa = new DBHangHoa(ChiTietMon.this);
    }
}