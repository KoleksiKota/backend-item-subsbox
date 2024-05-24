package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import java.net.MalformedURLException;

public interface Component {
    String getName();

    void setPicture(String url) throws MalformedURLException;

    Integer calculatePrice();
}