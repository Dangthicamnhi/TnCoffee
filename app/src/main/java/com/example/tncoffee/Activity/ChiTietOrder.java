package com.example.tncoffee.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tncoffee.Model.HangHoa;
import com.example.tncoffee.Model.Order;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

public class ChiTietOrder extends AppCompatActivity {
    boolean isAdded;
    Order order;
    SanPham sanPham;
    TextView tvTenSP, tvDonGia, tvThanhTien;
    EditText edtSoLuong;
    Button btnPlus, btnMinus, btnCapNhat, btnXoa, btnOrder, btnHuy;
    ImageView ivHinh;
    LinearLayout llChitietOrder, llOrder;
    int[] hinh = {R.drawable.ic_bxiu, R.drawable.ic_cfden, R.drawable.ic_cfsua, R.drawable.ic_tchanh,
            R.drawable.ic_tdao, R.drawable.ic_tsua, R.drawable.ic_banhngot, R.drawable.banhdau, R.drawable.banhran};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_order);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chi Tiết Order");
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        setControls();
        setEvents();
    }

    private void setEvents() {
        NhanGiaTri();
        edtSoLuong.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                try {
                    if (Integer.parseInt(edtSoLuong.getText().toString()) < 0) {
                        edtSoLuong.setText("0");
                    }
                } catch (Exception ex) {
                    edtSoLuong.setText("0");
                }

                return false;
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(edtSoLuong.getText().toString()) + 1;
                edtSoLuong.setText(sl + "");
                tvThanhTien.setText(tinhThanhTien() + " VNĐ");
                OrderMon.giohang.increaseQuantity(sanPham,sl);
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(edtSoLuong.getText().toString()) - 1;
                if (sl < 0) {
                    sl = 0;
                }
                edtSoLuong.setText(sl + "");
                tvThanhTien.setText(tinhThanhTien() + " VNĐ");
                OrderMon.giohang.decreaseQuantity(sanPham);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int soLuong = Integer.parseInt(edtSoLuong.getText().toString());
                if (soLuong > 0) {
                    Order or = new Order(sanPham.getMa(), soLuong, tinhThanhTien());
                    boolean isExist = false;
                    for (Order item : OrderMon.data_Order) {
                        if (item.getMaOrder().equals(or.getMaOrder())) {
                            OrderMon.data_Order.get(OrderMon.data_Order.indexOf(item)).setSoLuong(item.getSoLuong() + or.getSoLuong());
                            OrderMon.data_Order.get(OrderMon.data_Order.indexOf(item)).setThanhTien(item.getThanhTien() + or.getThanhTien());
                            isExist = true;
                            break;
                        }
                    }
                    if (!isExist) {
                        OrderMon.data_Order.add(or);
                    }
                    OrderMon.giohang.add(new HangHoa(sanPham.getMa(), sanPham.getTen(), sanPham.getGia(), sanPham.getLoai(), soLuong + ""));

                }
                onBackPressed();
            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ChiTietOrder.this);
                builder.setMessage("Bạn có muốn xóa sản phẩm " + sanPham.getTen() + " khỏi giỏ hàng ?");
                builder.setPositiveButton("Có", (dialog, which) -> {
                    for (Order item : OrderMon.data_Order) {
                        if (item.getMaOrder().equals(order.getMaOrder())) {
                            OrderMon.data_Order.remove(item);
                            OrderMon.adapter_Order.notifyDataSetChanged();
                            OrderMon.tvTongTien.setText(OrderMon.tinhTongTien(OrderMon.data_Order) + " VNĐ");
                            onBackPressed();
                            break;
                        }
                    }
                });
                builder.setNegativeButton("Không", (dialog, which) -> {
                    // Nếu người dùng chọn không, đóng hộp thoại
                    dialog.dismiss();
                });
                builder.create().show();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setControls() {
        ivHinh = findViewById(R.id.ivHinh);
        tvTenSP = findViewById(R.id.tvTenOrder);
        tvDonGia = findViewById(R.id.tvDonGia);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        edtSoLuong = findViewById(R.id.edtSoLuong);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnXoa);
        btnOrder = findViewById(R.id.btnOrder);
        btnHuy = findViewById(R.id.btnHuy);
        llOrder = findViewById(R.id.llOrder);
        llChitietOrder = findViewById(R.id.llChitietOrder);
    }

    private void NhanGiaTri() {
        isAdded = (boolean) getIntent().getSerializableExtra("isAdded");
        sanPham = (SanPham) getIntent().getSerializableExtra("item");

        if (sanPham.getLoai().equals("Đồ Ăn")) {
            ivHinh.setImageResource(R.drawable.ic_doan);
        }
        if (sanPham.getLoai().equals("Nước Uống")) {
            ivHinh.setImageResource(R.drawable.ic_nuocuong);
        }

        tvTenSP.setText(sanPham.getTen());
        tvDonGia.setText(sanPham.getGia());

        edtSoLuong.setText("0");
        tvThanhTien.setText("0 VNĐ");

        // add vao gio hang


        if (!isAdded) {
            order = (Order) getIntent().getSerializableExtra("order");
            edtSoLuong.setText(order.getSoLuong() + "");
            tvThanhTien.setText(tinhThanhTien() + " VNĐ");
            llOrder.setVisibility(View.GONE);
        } else {
            llChitietOrder.setVisibility(View.GONE);
        }
    }

    private int tinhThanhTien() {
        int thanhTien = Integer.parseInt(edtSoLuong.getText().toString()) * Integer.parseInt(tvDonGia.getText().toString());
        return thanhTien;
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
}