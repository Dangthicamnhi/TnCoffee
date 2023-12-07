package com.example.tncoffee.Model;

import com.example.tncoffee.Database.ChiTietHoaDon;
import com.example.tncoffee.Database.GioHang;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Order extends ChiTietHoaDon implements Serializable {
    @Override
    public String toString() {
        return "Order{" +
                "maOrder='" + maOrder + '\'' +
                ", soLuong=" + soLuong +
                ", thanhTien=" + thanhTien +
                '}';
    }

    String maOrder;
    int soLuong, thanhTien;

    public Order() {
    }

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
