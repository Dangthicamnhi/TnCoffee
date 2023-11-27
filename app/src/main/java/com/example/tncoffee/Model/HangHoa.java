package com.example.tncoffee.Model;

import java.io.Serializable;
public class HangHoa implements Serializable {

    String MaHH, TenHH, Gia, SoLuong, Loai;
    private static int cafe = 0, duong = 0, botmy = 0, tra = 0;

    public HangHoa(String maHH, String tenHH, String gia, String soLuong, String loai) {
        MaHH = maHH;
        TenHH = tenHH;
        Gia = gia;
        SoLuong = soLuong;
        Loai = loai;
        if (loai.equals("Cafe")) {
            cafe++;
        } else if (loai.equals("Đường")) {
            duong++;
        } else if (loai.equals("Bột Mỳ")) {
            botmy++;
        } else if (loai.equals("Trà")) {
            tra++;
        }
    }

    public static void GiamSoLuong(String loai) {
        if (loai.equals("Cafe")) {
            cafe--;
        } else if (loai.equals("Đường")) {
            duong--;
        } else if (loai.equals("Bột Mỳ")) {
            botmy--;
        } else if (loai.equals("Trà")) {
            tra--;
        }
    }

    public static void ThayDoiSoLuong(String loaiGiam, String loaiTang) {
        if (loaiGiam.equals("Cafe")) {
            cafe--;
        } else if (loaiGiam.equals("Đường")) {
            duong--;
        } else if (loaiGiam.equals("Bột Mỳ")) {
            botmy--;
        } else if (loaiGiam.equals("Trà")) {
            tra--;
        }
        if (loaiTang.equals("Cafe")) {
            cafe++;
        } else if (loaiTang.equals("Đường")) {
            duong++;
        } else if (loaiTang.equals("Bột Mỳ")) {
            botmy++;
        } else if (loaiTang.equals("Trà")) {
            tra++;
        }
    }

    public static String ThongKe() {
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

    public static int getCafe() {
        return cafe;
    }

    public static void setCafe(int cafe) {
        HangHoa.cafe = cafe;
    }

    public static int getDuong() {
        return duong;
    }

    public static void setDuong(int duong) {
        HangHoa.duong = duong;
    }

    public static int getBotmy() {
        return botmy;
    }

    public static void setBotmy(int botmy) {
        HangHoa.botmy = botmy;
    }

    public static int getTra() {
        return tra;
    }

    public static void setTra(int tra) {
        HangHoa.tra = tra;
    }
}