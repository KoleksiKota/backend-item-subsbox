package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import java.net.MalformedURLException;
import java.net.URL;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item implements Component {
    private String itemId;
    private String name;
    private String description;
    @Setter(AccessLevel.NONE)
    private URL picture;
    @Getter(AccessLevel.NONE)
    private Integer price;

    @SuppressWarnings("deprecation")
    public void setPicture(String url) throws MalformedURLException {
        this.picture = new URL(url);
    }

    public Integer calculatePrice() {
        return this.price;
    }
}
