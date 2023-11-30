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
import com.example.tncoffee.Model.Order;
import com.example.tncoffee.R;

import java.util.List;

public class Adapter_Order extends ArrayAdapter {

    Context context;
    int resource;
    List<Order> danhSach;

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
        ImageView ivHinhSanPham = convertView.findViewById(R.id.ivHinhSanPham);
        ImageView btnTangSLSanPham = convertView.findViewById(R.id.ivTangSLSanPham);
        TextView tvMaOrder = convertView.findViewById(R.id.tvTenSanPham);
        Order order = danhSach.get(position);
        tvMaOrder.setText(order.getMaOrder());

        btnTangSLSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });

        //hien thi toan bo du lieu len view hop le
        return convertView;
    }
}
