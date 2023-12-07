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
import com.example.tncoffee.R;

import java.util.List;

public class Custom_Adapter_HangHoa extends ArrayAdapter {

    Context context;
    int resource;
    List<HangHoa> danhSach;

    public Custom_Adapter_HangHoa(Context context, int resource, List<HangHoa> danhSach) {
        super(context, resource, danhSach);
        this.context = context;
        this.resource = resource;
        this.danhSach = danhSach;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);
        ImageView ivHinh = convertView.findViewById(R.id.ivHinhSanPham);
        TextView tvMa = convertView.findViewById(R.id.tvMaHH);
        TextView tvTen = convertView.findViewById(R.id.tvTenHH);
        TextView tvSL = convertView.findViewById(R.id.tvSoLuong);
        TextView tvGia = convertView.findViewById(R.id.tvGia);

        HangHoa hangHoa = danhSach.get(position);

        tvMa.setText(hangHoa.getMa());
        tvTen.setText(hangHoa.getTen());
        tvSL.setText(hangHoa.getSoLuong());
        tvGia.setText(hangHoa.getGia());
        if (hangHoa.getLoai() != null) {
            if (hangHoa.getLoai().equals("Cafe")) {
                ivHinh.setImageResource(R.drawable.ic_cafe);
            }
            if (hangHoa.getLoai().equals("Đường")) {
                ivHinh.setImageResource(R.drawable.ic_duong);
            }
            if (hangHoa.getLoai().equals("Bột Mỳ")) {
                ivHinh.setImageResource(R.drawable.ic_botmy);
            }
            if (hangHoa.getLoai().equals("Trà")) {
                ivHinh.setImageResource(R.drawable.ic_tra);
            }
        } else {
            ivHinh.setImageResource(R.drawable.ic_cafe);
        }
        return convertView;
    }
}
