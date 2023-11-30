package com.example.tncoffee.Model;

import java.io.Serializable;

public class Order implements Serializable {

    String MaOrder , SoLuong;

    @Override
    public String toString() {
        return "Order{" +
                "MaOrder='" + MaOrder + '\'' +
                ", SoLuong='" + SoLuong + '\'' +
                '}';
    }

    public String getMaOrder() {
        return MaOrder;
    }

    public void setMaOrder(String maOrder) {
        MaOrder = maOrder;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }
}
