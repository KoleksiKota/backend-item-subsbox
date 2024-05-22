package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.repository;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Box;
import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;

@ExtendWith(MockitoExtension.class)
public class BoxRepositoryTest {
    @Mock
    BoxRepository boxRepository;
    List<Box> boxes;

    @BeforeEach
    void setup() throws MalformedURLException {
        boxes = new ArrayList<>();

        Item item1 = new Item();
        item1.setId("75d36b9a-3a36-4661-816e-4789aeb9d69a");
        item1.setName("Kemeja Batik Motif Sidoluhur");
        item1.setDescription("Batik khas Yogyakarta");
        item1.setPicture("https://batiks128.com/wp-content/uploads/products_img/LP6653BTF-M-300-sidoluhur.jpg");
        item1.setPrice(100000);

        Item item2 = new Item();
        item2.setId("97cf9e70-60b2-400c-906e-ee4a76d81ad0");
        item2.setName("Bakpia Pathok 25");
        item2.setDescription("100% Asli Yogyakarta");
        item2.setPicture("https://img.ws.mms.shopee.co.id/654fdb47f82ec541eca2acb06f5fb820");
        item2.setPrice(40000);

        Item item3 = new Item();
        item3.setId("0b68dc36-a21b-450f-92e8-0443a98a96ad");
        item3.setName("Blangkon Lipat");
        item3.setDescription("Kain penutup kepala tradisional");
        item3.setPicture("https://down-id.img.susercontent.com/file/310b330aa902dfffe69117ca742acb84");
        item3.setPrice(60000);

        ArrayList<Item> items1 = new ArrayList<>();
        items1.add(item1);
        items1.add(item2);

        Box box1 = new Box();
        box1.setId("7c59dda6-dec6-4694-8ed2-34d14db3c610");
        box1.setName("Box Yogyakarta 1");
        box1.setDescription("Versi 1");
        box1.setPicture(
                "https://terasmalioboro.jogjaprov.go.id/wp-content/uploads/2022/08/yogyakarta-monument.jpg");
        box1.setItems(items1);
        box1.calculatePrice();
        boxes.add(box1);

        ArrayList<Item> items2 = new ArrayList<>();
        items2.add(item2);
        items2.add(item3);

        Box box2 = new Box();
        box2.setId("5c9195af-085b-4c18-a77f-9eda5aeb6a51");
        box2.setName("Box Yogyakarta 2");
        box2.setDescription("Versi 2");
        box2.setPicture(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkETzYWWefz9GC1oZA9y7VIgs9DN0v459r9n7BdNy4cA&s");
        box2.setItems(items2);
        box2.calculatePrice();
        boxes.add(box2);
    }

    @Test
    void testCreateBox() {
        Box box1 = boxes.get(0);

        when(boxRepository.save(box1)).thenReturn(box1);
        Box result = boxRepository.save(box1);

        verify(boxRepository, times(1)).save(box1);
        assertEquals(box1, result);
    }

    @Test
    void testFindAll() {
        when(boxRepository.findAll()).thenReturn(boxes);
        List<Box> result = boxRepository.findAll();

        verify(boxRepository, times(1)).findAll();
        assertIterableEquals(boxes, result);
    }

    @Test
    void testFindByIdFound() {
        Box box1 = boxes.get(0);

        when(boxRepository.findById(box1.getId())).thenReturn(Optional.of(box1));
        Optional<Box> result = boxRepository.findById(box1.getId());

        verify(boxRepository, times(1)).findById(box1.getId());
        assertEquals(box1, result.get());
    }

    @Test
    void testFindByIdNotFound() {
        when(boxRepository.findById("invalidId")).thenReturn(null);
        Optional<Box> result = boxRepository.findById("invalidId");

        verify(boxRepository, times(1)).findById("invalidId");
        assertNull(result);
    }

    @Test
    void testEditBox() throws MalformedURLException {
        Box box1 = boxes.get(0);
        Box box2 = boxes.get(1);

        box1.setName(box2.getName());
        box1.setDescription(box2.getDescription());
        box1.setPicture(box2.getPicture().toString());
        box1.setItems(box2.getItems());
        box1.calculatePrice();

        when(boxRepository.save(box1)).thenReturn(box1);
        Box updatedBox1 = boxRepository.save(box1);

        verify(boxRepository, times(1)).save(box1);
        assertEquals(box1.getId(), updatedBox1.getId());
        assertEquals(box2.getName(), updatedBox1.getName());
        assertEquals(box2.getDescription(), updatedBox1.getDescription());
        assertEquals(box2.getPicture(), updatedBox1.getPicture());
        assertEquals(box2.calculatePrice(), updatedBox1.calculatePrice());
    }

    @Test
    void testDeleteBox() {
        Box box2 = boxes.get(1);

        doNothing().when(boxRepository).delete(box2);
        boxRepository.delete(box2);

        verify(boxRepository, times(1)).delete(box2);
    }
}
