package com.example.tncoffee.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tncoffee.Model.NhanVien;
import com.example.tncoffee.R;

import java.util.List;

public class Custom_Adapter extends ArrayAdapter {

    Context context;
    int resource;
    List<NhanVien> danhSach;

    public Custom_Adapter(Context context, int resource, List<NhanVien> danhSach) {
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
        TextView tvMaNV = convertView.findViewById(R.id.tvMaNV);
        TextView tvTenNV = convertView.findViewById(R.id.tvTenNV);
        TextView tvSDTH = convertView.findViewById(R.id.tvSDTH);
        TextView tvCCCD = convertView.findViewById(R.id.tvCCCD);
        NhanVien nv = danhSach.get(position);
        tvMaNV.setText(nv.getMaNV());
        tvTenNV.setText(nv.getTenNV());
        tvSDTH.setText(nv.getSDTH());
        tvCCCD.setText(nv.getCCCD());
        if (nv.getPhong().equals("Phòng 1")){
            ivHinh.setImageResource(R.drawable.ic_nv1);
        }if (nv.getPhong().equals("Phòng 2")){
            ivHinh.setImageResource(R.drawable.ic_nv2);
        }if (nv.getPhong().equals("Phòng 3")){
            ivHinh.setImageResource(R.drawable.ic_nv3);
        }
        return convertView;
    }
}