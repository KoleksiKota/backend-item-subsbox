package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Box;

import java.util.List;

public interface BoxService {
    Box create(Box box);

    List<Box> findAll();

    Box findById(String boxId);

    Box edit(String boxId, Box updatedBox);

    void deleteBoxById(String boxId);
}
