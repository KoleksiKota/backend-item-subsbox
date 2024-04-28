package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Box implements Component {
    private String id;
    private String name;
    private String description;
    @Setter(AccessLevel.NONE)
    private URL picture;
    private ArrayList<Item> items;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer price;

    @SuppressWarnings("deprecation")
    public void setPicture(String url) throws MalformedURLException {
        this.picture = new URL(url);
    }

    public Integer calculatePrice() {
        Integer totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.calculatePrice();
        }

        this.price = (int) Math.floor(totalPrice);
        return this.price;
    }
}
