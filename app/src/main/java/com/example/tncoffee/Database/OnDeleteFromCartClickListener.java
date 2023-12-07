package com.example.tncoffee.Database;


import com.example.tncoffee.Model.HangHoa;

public interface OnDeleteFromCartClickListener {

    void onDeleteCartItemClicked(HangHoa product);

    void onIncreaseCartItemClicked(HangHoa product);

    void onDecreaseCartItemClicked(HangHoa product);
}
