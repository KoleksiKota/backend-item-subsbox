package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service;

import java.util.List;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;

public interface ItemService {
    Item create(Item item);

    List<Item> findAll();

    Item findById(String itemId);

    Item edit(String itemId, Item updatedItem);

    void deleteItemById(String itemId);
}
