package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class BoxController {
    @Autowired
    private BoxServiceImpl boxService;

    @PostMapping("/create")
    public ResponseEntity<Box> createBox(@RequestBody Box box) {
        Box createdBox = boxService.create(box);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBox);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Box>> getAllBoxs() {
        List<Box> boxs = boxService.findAll();
        return ResponseEntity.ok().body(boxs);
    }

    @GetMapping("/{boxId}")
    public ResponseEntity<Box> getBoxById(@PathVariable("boxId") String boxId) {
        try {
            Box box = boxService.findById(boxId);
            return ResponseEntity.ok().body(box);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/edit/{boxId}")
    public ResponseEntity<Box> updateBox(@PathVariable("boxId") String boxId, @RequestBody Box updatedBox) {
        try {
            Box box = boxService.edit(boxId, updatedBox);
            return ResponseEntity.ok().body(box);

        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/delete/{boxId}")
    public ResponseEntity<Void> deleteBoxById(@PathVariable("boxId") String boxId) {
        boxService.deleteBoxById(boxId);
        return ResponseEntity.noContent().build();
    }
}
