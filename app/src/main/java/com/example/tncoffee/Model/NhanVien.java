package com.example.tncoffee.Model;

import java.io.Serializable;
import java.util.List;

public class NhanVien implements Serializable {

    String MaNV, TenNV, SDTH, CCCD, Phong;

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getTenNV() {
        return TenNV;
    }

    public void setTenNV(String tenNV) {
        TenNV = tenNV;
    }

    public String getSDTH() {
        return SDTH;
    }

    public void setSDTH(String SDTH) {
        this.SDTH = SDTH;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }


    @Override
    public String toString() {
        return "NhanVien{" +
                "MaNV='" + MaNV + '\'' +
                ", TenNV='" + TenNV + '\'' +
                " , SDTH ='" + SDTH + '\'' +
                " , CCCD ='" + CCCD + '\'' +
                ", Phong='" + Phong + '\'' +
                '}';
    }

    public String getPhong() {
        return Phong;
    }

    public void setPhong(String phong) {
        Phong = phong;
    }

    public NhanVien(String maNV, String tenNV, String sDTH, String cccd, String phong) {
        MaNV = maNV;
        TenNV = tenNV;
        SDTH = sDTH;
        CCCD = cccd;
        Phong = phong;
    }

    public static String ThongKe(List<NhanVien> lnv) {
        int phong1 = 0, phong2 = 0, phong3 = 0;
        for (NhanVien nv : lnv) {
            switch (nv.getPhong()) {
                case "Phòng 1":
                    phong1++;
                    break;
                case "Phòng 2":
                    phong2++;
                    break;
                case "Phòng 3":
                    phong3++;
                    break;
            }
        }
        return"Phòng 1: "+phong1+"\nPhòng 2: "+phong2+"\nPhòng 3: "+phong3;
    }

}