package com.example.tncoffee.Model;

import java.io.Serializable;

public class HoaDon  implements Serializable {

    String maHD , ngayXuat ;
    int thanhTien;

    public HoaDon(String maHD, String ngayXuat, int thanhTien) {
        this.maHD = maHD;
        this.ngayXuat = ngayXuat;
        this.thanhTien = thanhTien;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
