package com.example.tncoffee.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String ma, ten, gia, loai;
    private static int sLDoAn = 0, sLNuocUong = 0;

    public SanPham(String ma, String ten, String gia, String loai) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        this.loai = loai;
        if (loai.equals("Đồ ăn")){
            sLDoAn++;
        }else if (loai.equals("Nước uống")){
            sLNuocUong++;
        }
    }

    public static String ThongKe(){
        return "Số lượng đồ ăn: " + sLDoAn +
                "\nSố lượng nước uống: " + sLNuocUong ;
    }

    public static void GiamSoLuong(String loai) {
        if (loai.equals("Đồ ăn")){
            sLDoAn--;
        }else if (loai.equals("Nước uống")){
            sLNuocUong--;
        }
    }

    public static void ThayDoiSoLuong(String loaiGiam, String loaiTang) {
        if (loaiGiam.equals("Đồ ăn")){
            sLDoAn--;
        }else if (loaiGiam.equals("Nước uống")){
            sLNuocUong--;
        }
        if (loaiTang.equals("Đồ ăn")){
            sLDoAn++;
        }else if (loaiTang.equals("Nước uống")){
            sLNuocUong++;
        }
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    @Override
    public String toString() {
        return "Thông Tin:\nMã: " + ma + ", Tên: " + ten + "\nLoại: " + loai + ", Giá: " + gia + "}";
    }


}
