package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Item implements Component {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String description;
    @Setter(AccessLevel.NONE)
    private URL picture;
    @Getter(AccessLevel.NONE)
    private Integer price;

    @JsonIgnore
    @ManyToMany(mappedBy = "items")
    private List<Box> boxes;

    @SuppressWarnings("deprecation")
    public void setPicture(String url) throws MalformedURLException {
        this.picture = new URL(url);
    }

    @JsonProperty("price")
    public Integer calculatePrice() {
        return this.price;
    }
}
