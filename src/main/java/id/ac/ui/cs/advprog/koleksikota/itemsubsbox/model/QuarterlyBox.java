package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import java.util.ArrayList;

public class QuarterlyBox extends Box {
    public QuarterlyBox() {
        super();
    }

    public String getName() {
        return "QTR - " + name;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
        this.calculatePrice();
    }

    // Untuk mendapatkan untung, harga akan dinaikkan sebesar 10%
    public Integer calculatePrice() {
        Integer totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.calculatePrice();
        }

        double finalPrice = totalPrice * 1.10;
        int roundedFinalPrice = (int) Math.floor(finalPrice);
        this.price = roundedFinalPrice;
        return roundedFinalPrice;
    }
}
