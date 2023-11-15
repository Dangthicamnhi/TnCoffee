package com.example.tncoffee.Moder;

import java.io.Serializable;

public class SanPham implements Serializable {
    String maSanPham , TenSP ;

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String tenSP) {
        TenSP = tenSP;
    }

}
