package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Box implements Component {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    @Setter(AccessLevel.NONE)
    private URL picture;
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Integer price;

    @ManyToMany
    @JoinTable(name = "box_item", joinColumns = @JoinColumn(name = "box_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
    @Setter(AccessLevel.NONE)
    private List<Item> items;

    @SuppressWarnings("deprecation")
    public void setPicture(String url) throws MalformedURLException {
        this.picture = new URL(url);
    }

    public void setItems(List<Item> items) {
        this.items = items;
        this.calculatePrice();
    }

    @JsonProperty("price")
    public Integer calculatePrice() {
        Integer totalPrice = 0;
        for (Item item : items) {
            totalPrice += item.calculatePrice();
        }

        this.price = (int) Math.floor(totalPrice);
        return this.price;
    }
}
