package com.example.tncoffee.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private String ma, ten, gia, Loai;
    private static int DoAn = 0, NuocUong = 0;

    public SanPham(String ma, String ten, String gia, String loai) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
       Loai = loai;
        if (loai.equals("Đồ ăn")){
            DoAn++;
        }else if (loai.equals("Nước uống")){
            NuocUong++;
        }
    }

    public static String ThongKe(){
        return "Số lượng đồ ăn: " + DoAn +
                "\nSố lượng nước uống: " + NuocUong ;
    }

    public static void GiamSoLuong(String loai) {
        if (loai.equals("Đồ ăn")){
            DoAn--;
        }else if (loai.equals("Nước uống")){
            NuocUong--;
        }
    }

    public static void ThayDoiSoLuong(String loaiGiam, String loaiTang) {
        if (loaiGiam.equals("Đồ ăn")){
            DoAn--;
        }else if (loaiGiam.equals("Nước uống")){
            NuocUong--;
        }
        if (loaiTang.equals("Đồ ăn")){
            DoAn++;
        }else if (loaiTang.equals("Nước uống")){
            NuocUong++;
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
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }

    @Override
    public String toString() {
        return "Thông Tin:\nMã: " + ma + ", Tên: " + ten + "\nLoại: " + Loai + ", Giá: " + gia + "}";
    }


}
