package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.repository;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;

@ExtendWith(MockitoExtension.class)
public class ItemRepositoryTest {
    @Mock
    ItemRepository itemRepository;
    List<Item> items;

    @BeforeEach
    void setup() throws MalformedURLException {
        items = new ArrayList<>();

        Item item1 = new Item();
        item1.setId("b3732e14-4e32-424c-bbf9-99c493382c40");
        item1.setName("Kemeja Batik Mega Mendung");
        item1.setDescription("Batik khas Cirebon");
        item1.setPicture("https://down-id.img.susercontent.com/file/83ccb3b4fa94a584ebb165e3e8db1737");
        item1.setPrice(80000);
        items.add(item1);

        Item item2 = new Item();
        item2.setId("75d36b9a-3a36-4661-816e-4789aeb9d69a");
        item2.setName("Kemeja Batik Motif Sidoluhur");
        item2.setDescription("Batik khas Yogyakarta");
        item2.setPicture("https://batiks128.com/wp-content/uploads/products_img/LP6653BTF-M-300-sidoluhur.jpg");
        item2.setPrice(100000);
        items.add(item2);
    }

    @AfterEach
    void cleanup() {
        items.clear();
    }

    @Test
    void testCreateItem() {
        Item item1 = items.get(0);

        when(itemRepository.save(item1)).thenReturn(item1);
        Item result = itemRepository.save(item1);

        verify(itemRepository, times(1)).save(item1);
        assertEquals(item1, result);
    }

    @Test
    void testFindAll() {
        when(itemRepository.findAll()).thenReturn(items);
        List<Item> result = itemRepository.findAll();

        verify(itemRepository, times(1)).findAll();
        assertIterableEquals(items, result);
    }

    @Test
    void testFindByIdFound() {
        Item item1 = items.get(0);

        when(itemRepository.findById(item1.getId())).thenReturn(Optional.of(item1));
        Optional<Item> result = itemRepository.findById(item1.getId());

        verify(itemRepository, times(1)).findById(item1.getId());
        assertEquals(item1, result.get());
    }

    @Test
    void testFindByIdNotFound() {
        when(itemRepository.findById("invalidId")).thenReturn(null);
        Optional<Item> result = itemRepository.findById("invalidId");

        verify(itemRepository, times(1)).findById("invalidId");
        assertNull(result);
    }

    @Test
    void testEditItem() throws MalformedURLException {
        Item item1 = items.get(0);
        Item item2 = items.get(1);

        item1.setName(item2.getName());
        item1.setDescription(item2.getDescription());
        item1.setPicture(item2.getPicture().toString());
        item1.setPrice(item2.calculatePrice());

        when(itemRepository.save(item1)).thenReturn(item1);
        Item updatedItem1 = itemRepository.save(item1);

        verify(itemRepository, times(1)).save(item1);
        assertEquals(item1.getId(), updatedItem1.getId());
        assertEquals(item2.getName(), updatedItem1.getName());
        assertEquals(item2.getDescription(), updatedItem1.getDescription());
        assertEquals(item2.getPicture(), updatedItem1.getPicture());
        assertEquals(item2.calculatePrice(), updatedItem1.calculatePrice());
    }

    @Test
    void testDeleteItem() {
        Item item1 = items.get(0);

        doNothing().when(itemRepository).delete(item1);
        itemRepository.delete(item1);

        verify(itemRepository, times(1)).delete(item1);
    }
}
