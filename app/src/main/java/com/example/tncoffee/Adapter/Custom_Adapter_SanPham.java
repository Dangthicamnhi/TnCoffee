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

import com.example.tncoffee.Model.NhanVien;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.util.List;

public class Custom_Adapter_SanPham extends ArrayAdapter {
    Context context;
    int resource;
    List<SanPham> danhSach;

    public Custom_Adapter_SanPham(Context context, int resource, List<SanPham> danhSach) {
        super(context , resource , danhSach );
        this.context = context;
        this.resource = resource;
        this.danhSach = danhSach;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        ImageView ivHinh = convertView.findViewById(R.id.ivAnh);
        TextView tvMaMon = convertView.findViewById(R.id.tvMaMon);
        TextView tvTenMon = convertView.findViewById(R.id.tvTenMon);
        TextView tvGia= convertView.findViewById(R.id.tvGiaMon);
        SanPham nv = danhSach.get(position);
        tvMaMon.setText(nv.getMa());
        tvTenMon.setText(nv.getTen());
        tvGia.setText(nv.getGia());
        if (nv.getLoai().equals("Đồ Ăn")){
            ivHinh.setImageResource(R.drawable.ic_doan);
        }if (nv.getLoai().equals("Nước Uống")){
            ivHinh.setImageResource(R.drawable.ic_nuocuong);
        }
        return convertView;
    }
}
