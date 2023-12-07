package com.example.tncoffee.Model;

import java.io.Serializable;
import java.util.List;

public class SanPham implements Serializable {
    private String ma, ten, gia, Loai;

    public SanPham(){}
    public SanPham(String ma, String ten, String gia, String loai) {
        this.ma = ma;
        this.ten = ten;
        this.gia = gia;
        Loai = loai;
    }

    /**
     * Thống kê theo sản phẩm bán được
     *
     * @param lsp
     * @return
     */
    public static String ThongKeSP(List<SanPham> lsp) {
        int DoAn = 0, NuocUong = 0;
        for (SanPham sp : lsp) {
            if(sp.getLoai() != null){
                switch (sp.getLoai()) {
                    case "Đồ Ăn":
                        DoAn++;
                        break;
                    case "Nước Uống":
                        NuocUong++;
                        break;
                }
            }
        }

        return "Số lượng đồ ăn: " + DoAn +
                "\nSố lượng nước uống: " + NuocUong;
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
