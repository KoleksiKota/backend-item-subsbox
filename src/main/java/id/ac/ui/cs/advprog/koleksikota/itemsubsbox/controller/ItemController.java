package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.controller;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service.ItemServiceImpl;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;

    @PostMapping("/create")
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item createdItem = itemService.create(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> items = itemService.findAll();
        return ResponseEntity.ok().body(items);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(@PathVariable("itemId") String itemId) {
        try {
            Item item = itemService.findById(itemId);
            return ResponseEntity.ok().body(item);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/edit/{itemId}")
    public ResponseEntity<Item> updateItem(@PathVariable("itemId") String itemId, @RequestBody Item updatedItem) {
        try {
            Item item = itemService.edit(itemId, updatedItem);
            return ResponseEntity.ok().body(item);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete/{itemId}")
    public ResponseEntity<Void> deleteItemById(@PathVariable("itemId") String itemId) {
        itemService.deleteItemById(itemId);
        return ResponseEntity.noContent().build();
    }
}
