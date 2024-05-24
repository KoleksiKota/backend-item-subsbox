package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;
import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item create(Item item) {
        itemRepository.save(item);
        return item;
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item findById(String itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        return item.orElseThrow(() -> new NoSuchElementException("Item with ID " + itemId + " not found"));
    }

    @Override
    public Item edit(String itemId, Item updatedItem) {
        updatedItem.setId(itemId);
        itemRepository.save(updatedItem);
        return updatedItem;
    }

    @Override
    public void deleteItemById(String itemId) {
        itemRepository.deleteById(itemId);
    }
}
