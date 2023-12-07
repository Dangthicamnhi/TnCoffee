package com.example.tncoffee.Database;

import com.example.tncoffee.Model.SanPham;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class GioHang {

    // class of member
    private HashMap<SanPham, Integer> giohang;


    //constructor
    public GioHang() {
        giohang = new HashMap<>();
    }

    //properties
    public HashMap<SanPham, Integer> getGiohang() {
        return giohang;
    }

    public void setGiohang(HashMap<SanPham, Integer> giohang) {
        this.giohang = giohang;
    }

    /**
     * Thêm 1 sản phẩm vào danh sách sản phẩm trong hóa đơn
     *
     * @param hangHoa
     */
    public void add(SanPham hangHoa) {
        int quantity = giohang.containsKey(hangHoa) ? giohang.get(hangHoa) : 0;
        giohang.put(hangHoa, quantity + 1);
    }

    /**
     * Xóa 1 sản phẩm ra khỏi giỏ hàng
     *
     * @param hangHoa
     */
    public boolean remove(SanPham hangHoa) {
        // Kiểm tra xem sản phẩm có trong giỏ hàng hay không
        if (contains(hangHoa)) {
            // Xóa sản phẩm khỏi giỏ hàng
            giohang.remove(hangHoa);
            return true;
        }
        return false;
    }

    /**
     * Phương thức này nhận vào một đối tượng HangHoa và trả về số lượng của sản phẩm đó trong giỏ hàng
     *
     * @param hangHoa
     * @return Phương thức này nhận vào một đối tượng HangHoa và trả về số lượng của sản phẩm đó trong giỏ hàng
     */
    public int getQuantity(SanPham hangHoa) {
        return giohang.containsKey(hangHoa) ? giohang.get(hangHoa) : 0;
    }

    /**
     * Phương thức này không nhận tham số nào và trả về tổng số lượng tất cả các sản phẩm trong giỏ hàng.
     *
     * @return Phương thức này không nhận tham số nào và trả về tổng số lượng tất cả các sản phẩm trong giỏ hàng.
     */
    public int getQuantity() {
        int totalQuantity = 0;
        for (int quantity : giohang.values()) {
            totalQuantity += quantity;
        }
        return totalQuantity;
    }

    /**
     * Đem danh sách sản phẩm trong hóa đơn (giỏ hàng) đi in cho người ta xem
     *
     * @return giỏ hàng sản phẩm(thanh toán hóa đơn)
     */
    public List<SanPham> getHangHoaList() {
        // Trả về một danh sách các sản phẩm trong giỏ hàng
        return new ArrayList<>(giohang.keySet());
    }

    /**
     * Kiểm tra sản phẩm trong giỏ hàng còn không
     *
     * @param hangHoa
     * @return
     */
    public boolean contains(SanPham hangHoa) {
        // Kiểm tra xem một sản phẩm có trong giỏ hàng hay không
        return giohang.containsKey(hangHoa);
    }

    /**
     * Tăng số lượng sản phẩm trong giỏ hàng
     *
     * @param hangHoa
     */
    public void increaseQuantity(SanPham hangHoa,Integer soluong) {
        // Tăng số lượng của một sản phẩm trong giỏ hàng
        int quantity = getQuantity(hangHoa);
        // Nếu số lượng hiện tại nhỏ hơn số lượng trong kho thì thêm số lượng giỏ hàng lên 1
        if (quantity <  soluong) {
            giohang.put(hangHoa, quantity + 1);
        }
    }

    /**
     * Giảm số lượng sản phẩm trong giỏ hàng
     *
     * @param hangHoa
     */
    public void decreaseQuantity(SanPham hangHoa) {
        // Kiểm tra xem sản phẩm có trong giỏ hàng hay không
        if (contains(hangHoa)) {
            // Lấy số lượng hiện tại của sản phẩm
            int quantity = getQuantity(hangHoa);
            // Nếu số lượng hiện tại lớn hơn 1, giảm số lượng
            if (quantity > 1) {
                giohang.put(hangHoa, quantity - 1);
            } else {
                // Nếu số lượng hiện tại bằng 1, xóa sản phẩm khỏi giỏ hàng
                giohang.remove(hangHoa);
            }
        }
    }

    /**
     * Tính tiền thanh toán giỏ hàng
     *
     * @return Tổng tiền giỏ hàng
     */
    public double getTongThanhTien() {
        double tongThanhTien = 0;
        for (Map.Entry<SanPham, Integer> entry : giohang.entrySet()) {
            SanPham hangHoa = entry.getKey();
            int quantity = entry.getValue();
            tongThanhTien += Integer.parseInt(hangHoa.getGia()) * quantity;
        }
        return tongThanhTien;
    }

    /**
     * Xóa hết sản phẩm trong giỏ hàng
     */
    public void clear() {
        // Xóa tất cả các sản phẩm khỏi giỏ hàng
        giohang.clear();
    }



}

