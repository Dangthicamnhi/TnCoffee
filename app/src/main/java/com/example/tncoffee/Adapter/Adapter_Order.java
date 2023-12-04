package com.example.tncoffee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tncoffee.Activity.MainActivity;
import com.example.tncoffee.Activity.QLMon;
import com.example.tncoffee.Model.Order;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.util.List;

public class Adapter_Order extends ArrayAdapter {

    Context context;
    int resource;
    List<Order> danhSach;
//    int[] hinh = {R.drawable.ic_bxiu, R.drawable.ic_cfden, R.drawable.ic_cfsua, R.drawable.ic_tchanh, R.drawable.ic_tdao, R.drawable.ic_tsua, R.drawable.ic_banhngot, R.drawable.banhdau , R.drawable.banhran};
    public Adapter_Order(Context context, int resource, List<Order> danhSach) {
        super(context , resource , danhSach );
        this.context = context;
        this.resource = resource;
        this.danhSach = danhSach;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        ImageView ivHinhOrder = convertView.findViewById(R.id.ivHinhSanPham);
        TextView tvTenOrder = convertView.findViewById(R.id.tvTenOrder);
        TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
        TextView tvThanhTien = convertView.findViewById(R.id.tvThanhTien);

        Order order = danhSach.get(position);
        tvTenOrder.setText(order.getMaOrder());
        tvSoLuong.setText("x"+danhSach.get(position).getSoLuong());
        tvThanhTien.setText(danhSach.get(position).getThanhTien() + "VNĐ");
        String loai = "";
        for(SanPham item : QLMon.danhSach){
            if(item.getMa().equals(order.getMaOrder())){
                loai = item.getLoai();
            }
        }
        if (loai.equals("Đồ Ăn")){
            ivHinhOrder.setImageResource(R.drawable.ic_doan);
        }if (loai.equals("Nước Uống")){
            ivHinhOrder.setImageResource(R.drawable.ic_nuocuong);
        }
        //hien thi toan bo du lieu len view hop le
        return convertView;
    }
}
