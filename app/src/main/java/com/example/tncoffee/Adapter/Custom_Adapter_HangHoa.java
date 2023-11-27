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

import com.example.tncoffee.Model.HangHoa;
import com.example.tncoffee.Model.SanPham;
import com.example.tncoffee.R;

import java.util.HashMap;
import java.util.List;

public class Custom_Adapter_HangHoa extends ArrayAdapter {

    Context context;
    int resource;
    List<HangHoa> danhSach;
    public Custom_Adapter_HangHoa(Context context, int resource, List<HangHoa> danhSach) {
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
        TextView tvMa = convertView.findViewById(R.id.tvMaHH);
        TextView tvTen = convertView.findViewById(R.id.tvTenHH);
        TextView tvSL = convertView.findViewById(R.id.tvSoLuong);
        TextView tvGia = convertView.findViewById(R.id.tvGia);
        HangHoa nv = danhSach.get(position);
        tvMa.setText(nv.getMaHH());
        tvTen.setText(nv.getTenHH());
        tvSL.setText(nv.getSoLuong());
        tvGia.setText(nv.getGia());
        if (nv.getLoai().equals("Cafe")){
            ivHinh.setImageResource(R.drawable.ic_cafe);
        }if (nv.getLoai().equals("Đường")){
            ivHinh.setImageResource(R.drawable.ic_duong);
        }if (nv.getLoai().equals("Bột Mỳ")){
            ivHinh.setImageResource(R.drawable.ic_botmy);
        }if (nv.getLoai().equals("Trà")){
            ivHinh.setImageResource(R.drawable.ic_tra);
        }
        return convertView;
    }
}
