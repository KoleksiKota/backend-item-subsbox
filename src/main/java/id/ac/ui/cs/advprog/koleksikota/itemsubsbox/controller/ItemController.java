package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.controller;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service.ItemServiceImpl;

@RestController
@RequestMapping("/item")
@CrossOrigin
@EnableAsync
public class ItemController {
    @Autowired
    private ItemServiceImpl itemService;

    @Async
    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<Item>> createItem(@RequestBody Item item) {
        Item createdItem = itemService.create(item);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(createdItem));
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<Item>>> getAllItems() {
        List<Item> items = itemService.findAll();
        return CompletableFuture.completedFuture(ResponseEntity.ok().body(items));
    }

    @GetMapping("/{itemId}")
    public CompletableFuture<ResponseEntity<Item>> getItemById(@PathVariable("itemId") String itemId) {
        try {
            Item item = itemService.findById(itemId);
            return CompletableFuture.completedFuture(ResponseEntity.ok().body(item));

        } catch (NoSuchElementException e) {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @PostMapping("/edit/{itemId}")
    public CompletableFuture<ResponseEntity<Item>> updateItem(@PathVariable("itemId") String itemId, @RequestBody Item updatedItem) {
        try {
            Item item = itemService.edit(itemId, updatedItem);
            return CompletableFuture.completedFuture(ResponseEntity.ok().body(item));

        } catch (NoSuchElementException e) {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @PostMapping("/delete/{itemId}")
    public CompletableFuture<ResponseEntity<Void>> deleteItemById(@PathVariable("itemId") String itemId) {
        itemService.deleteItemById(itemId);
        return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
    }
}
