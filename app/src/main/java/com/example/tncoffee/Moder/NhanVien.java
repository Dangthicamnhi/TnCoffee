package com.example.tncoffee.Moder;

import java.io.Serializable;

public class NhanVien implements Serializable {

    String MaNV,TenNV , SDTH , CCCD, Phong ;

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

    public static int getPhong1() {
        return Phong1;
    }

    public static void setPhong1(int phong1) {
        Phong1 = phong1;
    }

    public static int getPhong2() {
        return Phong2;
    }

    public static void setPhong2(int phong2) {
        Phong2 = phong2;
    }

    public static int getPhong3() {
        return Phong3;
    }

    public static void setPhong3(int phong3) {
        Phong3 = phong3;
    }

    private static int Phong1 = 0, Phong2= 0, Phong3 = 0;


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

    public NhanVien(String maNV, String tenNV  , String sDTH , String cccd, String phong) {
        MaNV = maNV;
        TenNV = tenNV;
        SDTH = sDTH ;
        CCCD = cccd ;
        Phong = phong;
        if(phong.equals("Phòng 1"))
        {
            Phong1++;
        }
        else if(phong.equals("Phòng 2"))
        {
            Phong2++;
        }
        else if(phong.equals("Phòng 3"))
        {
            Phong3++;
        }
    }
    public static String ThongKe(){
        return "Phòng 1: " + Phong1 +
                "\nPhòng 2: " + Phong2 +
                "\nPhòng 3: " + Phong3;
    }

    public static void GiamSoLuong(String phong) {
        if(phong.equals("Phòng 1"))
        {
            Phong1--;
        }
        else if(phong.equals("Phòng 2"))
        {
            Phong2--;
        }
        else if(phong.equals("Phòng 3"))
        {
            Phong3--;
        }
    }

    public static void ThayDoiSoLuong(String loaiGiam, String loaiTang) {
        if(loaiGiam.equals("Phòng 1"))
        {
            Phong1++;
        }
        else if(loaiGiam.equals("Phòng 2"))
        {
            Phong2++;
        }
        else if(loaiGiam.equals("Phòng 3"))
        {
            Phong3++;
        }
        if(loaiTang.equals("Phòng 1"))
        {
            Phong1++;
        }
        else if(loaiTang.equals("Phòng 2"))
        {
            Phong2++;
        }
        else if(loaiTang.equals("Phòng 3"))
        {
            Phong3++;
        }
    }

}