package com.example.tncoffee.Model;

import java.io.Serializable;
import java.util.HashMap;

public class Order implements Serializable {
 String maOrder ;
 int soLuong , thanhTien ;

    public Order(String maOrder, int soLuong, int thanhTien) {
        this.maOrder = maOrder;
        this.soLuong = soLuong;
        this.thanhTien = thanhTien;
    }

    public String getMaOrder() {
        return maOrder;
    }

    public void setMaOrder(String maOrder) {
        this.maOrder = maOrder;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(int thanhTien) {
        this.thanhTien = thanhTien;
    }
}
