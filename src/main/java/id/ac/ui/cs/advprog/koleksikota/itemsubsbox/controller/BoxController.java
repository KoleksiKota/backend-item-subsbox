package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Box;
import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service.BoxServiceImpl;

@RestController
@RequestMapping("/box")
@CrossOrigin
@EnableAsync
public class BoxController {
    @Autowired
    private BoxServiceImpl boxService;

    @PostMapping("/create")
    public CompletableFuture<ResponseEntity<Box>> createBox(@RequestBody Box box) {
        Box createdBox = boxService.create(box);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(createdBox));
    }

    @GetMapping("/list")
    public CompletableFuture<ResponseEntity<List<Box>>> getAllBoxs() {
        List<Box> boxs = boxService.findAll();
        return CompletableFuture.completedFuture(ResponseEntity.ok().body(boxs));
    }

    @GetMapping("/{boxId}")
    public CompletableFuture<ResponseEntity<Box>> getBoxById(@PathVariable("boxId") String boxId) {
        try {
            Box box = boxService.findById(boxId);
            return CompletableFuture.completedFuture(ResponseEntity.ok().body(box));

        } catch (NoSuchElementException e) {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @PostMapping("/edit/{boxId}")
    public CompletableFuture<ResponseEntity<Box>> updateBox(@PathVariable("boxId") String boxId, @RequestBody Box updatedBox) {
        try {
            Box box = boxService.edit(boxId, updatedBox);
            return CompletableFuture.completedFuture(ResponseEntity.ok().body(box));

        } catch (NoSuchElementException e) {
            return CompletableFuture.completedFuture(ResponseEntity.notFound().build());
        }
    }

    @PostMapping("/delete/{boxId}")
    public CompletableFuture<ResponseEntity<Void>> deleteBoxById(@PathVariable("boxId") String boxId) {
        boxService.deleteBoxById(boxId);
        return CompletableFuture.completedFuture(ResponseEntity.noContent().build());
    }
}
