package com.example.tncoffee.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tncoffee.Activity.QLHoaDon;
import com.example.tncoffee.Database.ChiTietHoaDon;
import com.example.tncoffee.Model.HangHoa;
import com.example.tncoffee.Model.HoaDon;
import com.example.tncoffee.R;

import java.util.List;
import java.util.Map;

public class Adapter_HoaDon extends ArrayAdapter {

    Context context;
    int resource;
    List<HoaDon> data;
    public Adapter_HoaDon(Context context, int resource, List<HoaDon> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource,null);
        TextView tvMaHD = convertView.findViewById(R.id.tvMaHD);
        TextView tvNgayLap = convertView.findViewById(R.id.tvNgayXuat);
        TextView tvThanhTien = convertView.findViewById(R.id.tvThanhTien);

        HoaDon hd = data.get(position);
        tvMaHD.setText(hd.getMaHD());
        tvNgayLap.setText(hd.getNgayXuat());
        tvThanhTien.setText(hd.getThanhTien() + " VNĐ");
        return convertView;
    }


}
