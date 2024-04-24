package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Box implements Component {
    protected String boxId;
    @Getter(AccessLevel.NONE)
    protected String name;
    protected String description;
    @Setter(AccessLevel.NONE)
    protected URL picture;
    @Setter(AccessLevel.NONE)
    protected ArrayList<Item> items;
    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    protected Integer price;

    public abstract String getName();

    @SuppressWarnings("deprecation")
    public void setPicture(String url) throws MalformedURLException {
        this.picture = new URL(url);
    }

    public abstract void setItems(ArrayList<Item> items);

    public abstract Integer calculatePrice();
}
