package com.example.tncoffee.Model;

import java.io.Serializable;
import java.util.List;

public class HangHoa implements Serializable {

    String MaHH, TenHH, Gia, SoLuong, Loai;

    public HangHoa(String maHH, String tenHH, String gia, String soLuong, String loai) {
        MaHH = maHH;
        TenHH = tenHH;
        Gia = gia;
        SoLuong = soLuong;
        Loai = loai;
    }

    public static String ThongKe(List<HangHoa> lhh) {

        int cafe = 0, duong = 0, botmy = 0, tra = 0;
        for (HangHoa sp : lhh) {
            switch (sp.getLoai()) {
                case "Cafe":
                    cafe++;
                    break;
                case "Đường":
                    duong++;
                    break;
                case "Bột My":
                    botmy++;
                    break;
                case "Trà":
                    tra++;
                    break;
            }
        }
        return "Cafe: " + cafe +
                "\nĐường: " + duong +
                "\nBột Mỳ: " + botmy +
                "\nTrà: " + tra;
    }

    public HangHoa() {
    }

    public String getMaHH() {
        return MaHH;
    }

    public void setMaHH(String maHH) {
        MaHH = maHH;
    }

    public String getTenHH() {
        return TenHH;
    }

    public void setTenHH(String tenHH) {
        TenHH = tenHH;
    }

    public String getGia() {
        return Gia;
    }

    public void setGia(String gia) {
        Gia = gia;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getLoai() {
        return Loai;
    }

    public void setLoai(String loai) {
        Loai = loai;
    }
}