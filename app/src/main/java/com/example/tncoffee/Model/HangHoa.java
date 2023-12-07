package com.example.tncoffee.Model;

import java.io.Serializable;
import java.util.List;

public class HangHoa extends SanPham implements Serializable {

    String SoLuong;//số lượng tồn kho

    public HangHoa() {
    }

    /**
     * =>Sản phẩm đặt món
     *
     * @param ma
     * @param ten
     * @param gia
     * @param loai
     */
    public HangHoa(String ma, String ten, String gia, String loai) {
        super(ma, ten, gia, loai);
    }

    /**
     * Sản phẩm nhập kho
     *
     * @param ma
     * @param ten
     * @param gia
     * @param loai
     * @param soLuong
     */
    public HangHoa(String ma, String ten, String gia, String loai, String soLuong) {
        super(ma, ten, gia, loai);
        SoLuong = soLuong;
    }

    /**
     * Thống kê hàng hóa trong kho
     *
     * @param hangHoas
     * @return
     */

    public static String ThongKeHH(List<HangHoa> hangHoas) {

        int cafe = 0, duong = 0, botmy = 0, tra = 0;
        for (HangHoa sp : hangHoas) {
            if (sp.getLoai() != null) {
                switch (sp.getLoai()) {
                    case "Cafe":
                        cafe++;
                        break;
                    case "Đường":
                        duong++;
                        break;
                    case "Bột Mỳ":
                        botmy++;
                        break;
                    case "Trà":
                        tra++;
                        break;
                }
            }
        }
        return "Cafe: " + cafe +
                "\nĐường: " + duong +
                "\nBột Mỳ: " + botmy +
                "\nTrà: " + tra;
    }


    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

}