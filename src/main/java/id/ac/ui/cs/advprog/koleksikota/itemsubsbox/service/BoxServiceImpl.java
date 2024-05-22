package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Box;
import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.repository.BoxRepository;

@Service
public class BoxServiceImpl implements BoxService {
    @Autowired
    private BoxRepository boxRepository;

    @Override
    public Box create(Box box) {
        boxRepository.save(box);
        return box;
    }

    @Override
    public List<Box> findAll() {
        return boxRepository.findAll();
    }

    @Override
    public Box findById(String boxId) {
        Optional<Box> box = boxRepository.findById(boxId);
        return box.orElseThrow(() -> new NoSuchElementException("Box with ID " + boxId + " not found"));
    }

    @Override
    public Box edit(String boxId, Box updatedBox) {
        updatedBox.setId(boxId);
        boxRepository.save(updatedBox);
        return updatedBox;
    }

    @Override
    public void deleteBoxById(String boxId) {
        boxRepository.deleteById(boxId);
    }
}
