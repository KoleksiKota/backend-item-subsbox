package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;
import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.repository.ItemRepository;

@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {
    @InjectMocks
    ItemServiceImpl itemService;
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
        doReturn(item1).when(itemRepository).save(item1);
        Item result = itemService.create(item1);

        verify(itemRepository, times(1)).save(item1);
        assertEquals(item1.getId(), result.getId());
    }

    @Test
    void testFindAll() {
        doReturn(items).when(itemRepository).findAll();
        List<Item> result = itemService.findAll();

        verify(itemRepository, times(1)).findAll();
        assertIterableEquals(items, result);
    }

    @Test
    void testFindByIdFound() {
        Item item1 = items.get(0);

        doReturn(Optional.of(item1)).when(itemRepository).findById(item1.getId());
        Item result = itemService.findById(item1.getId());

        verify(itemRepository, times(1)).findById(item1.getId());
        assertEquals(item1, result);
    }

    @Test
    void testFindByIdNotFound() {
        doThrow(NoSuchElementException.class).when(itemRepository).findById("invalidId");

        assertThrows(NoSuchElementException.class, () -> itemService.findById("invalidId"));
    }

    @Test
    void testEditItem() {
        Item item1 = items.get(0);
        Item item2 = items.get(1);
        item2.setId(item1.getId());

        doReturn(item2).when(itemRepository).save(item2);
        Item updatedItem1 = itemService.edit(item1.getId(), item2);

        verify(itemRepository, times(1)).save(item2);
        assertEquals(item1.getId(), updatedItem1.getId());
        assertEquals(item2.getName(), updatedItem1.getName());
        assertEquals(item2.getDescription(), updatedItem1.getDescription());
        assertEquals(item2.getPicture(), updatedItem1.getPicture());
        assertEquals(item2.calculatePrice(), updatedItem1.calculatePrice());
    }

    @Test
    void testDeleteById() {
        Item item1 = items.get(0);

        doNothing().when(itemRepository).deleteById(item1.getId());
        itemService.deleteItemById(item1.getId());

        verify(itemRepository, times(1)).deleteById(item1.getId());
    }
}
