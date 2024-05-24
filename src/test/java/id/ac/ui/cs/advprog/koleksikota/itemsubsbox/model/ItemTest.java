package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ItemTest {
    ArrayList<Item> items;

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
    }

    @AfterEach
    void cleanup() {
        this.items.clear();
    }

    @Test
    void testCreateItem() {
        Item item1 = items.get(0);
        assertEquals("b3732e14-4e32-424c-bbf9-99c493382c40", item1.getId());
        assertEquals("Kemeja Batik Mega Mendung", item1.getName());
        assertEquals("Batik khas Cirebon", item1.getDescription());
        assertEquals("https://down-id.img.susercontent.com/file/83ccb3b4fa94a584ebb165e3e8db1737",
                item1.getPicture().toString());
        assertEquals(80000, item1.calculatePrice());
    }

    @Test
    void testEditItem() {
        Item item1 = items.get(0);
        item1.setName("Kemeja Corak Mega Mendung");
        item1.setDescription("Spesial khas Cirebon");
        item1.setPrice(50000);

        assertEquals("Kemeja Corak Mega Mendung", item1.getName());
        assertEquals("Spesial khas Cirebon", item1.getDescription());
        assertEquals(50000, item1.calculatePrice());
    }

    @Test
    void testSetInvalidURL() {
        Item item1 = items.get(0);
        assertThrows(MalformedURLException.class, () -> {
            item1.setPicture("invalidURL");
        });
    }

    @Test
    void testAccessPictureFromURL() throws IOException {
        Item item1 = items.get(0);
        InputStream pictureStream = item1.getPicture().openStream();
        assertNotNull(pictureStream);
    }
}
