package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoxTest {
    ArrayList<Item> items;
    Box box;

    @BeforeEach
    void setup() throws MalformedURLException {
        items = new ArrayList<>();

        Item item1 = new Item();
        item1.setId("75d36b9a-3a36-4661-816e-4789aeb9d69a");
        item1.setName("Kemeja Batik Motif Sidoluhur");
        item1.setDescription("Batik khas Yogyakarta");
        item1.setPicture("https://batiks128.com/wp-content/uploads/products_img/LP6653BTF-M-300-sidoluhur.jpg");
        item1.setPrice(100000);
        items.add(item1);

        Item item2 = new Item();
        item2.setId("97cf9e70-60b2-400c-906e-ee4a76d81ad0");
        item2.setName("Bakpia Pathok 25");
        item2.setDescription("100% Asli Yogyakarta");
        item2.setPicture("https://img.ws.mms.shopee.co.id/654fdb47f82ec541eca2acb06f5fb820");
        item2.setPrice(40000);
        items.add(item2);

        Item item3 = new Item();
        item3.setId("0b68dc36-a21b-450f-92e8-0443a98a96ad");
        item3.setName("Blangkon Lipat");
        item3.setDescription("Kain penutup kepala tradisional");
        item3.setPicture("https://down-id.img.susercontent.com/file/310b330aa902dfffe69117ca742acb84");
        item3.setPrice(60000);
        items.add(item3);

        box = new Box();
        box.setId("7c59dda6-dec6-4694-8ed2-34d14db3c610");
        box.setName("Box Yogyakarta");
        box.setDescription("Berisi barang-barang terkini dari Yogyakarta!");
        box.setPicture(
                "https://terasmalioboro.jogjaprov.go.id/wp-content/uploads/2022/08/yogyakarta-monument.jpg");
        box.setItems(items);
    }

    @AfterEach
    void cleanup() {
        items.clear();
    }

    @Test
    void testCreateBox() {
        assertEquals("7c59dda6-dec6-4694-8ed2-34d14db3c610", box.getId());
        assertEquals("Box Yogyakarta", box.getName());
        assertEquals("Berisi barang-barang terkini dari Yogyakarta!",
                box.getDescription());
        assertEquals("https://terasmalioboro.jogjaprov.go.id/wp-content/uploads/2022/08/yogyakarta-monument.jpg",
                box.getPicture().toString());
        assertEquals((int) Math.floor(200000), box.calculatePrice());
        assertArrayEquals(items.toArray(), box.getItems().toArray());
    }

    @Test
    void testEditBox() throws MalformedURLException {
        box.setName("Yogyakarta Box");
        box.setDescription("Berisi barang-barang terbaru dari Yogyakarta!");
        box.setPicture(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkETzYWWefz9GC1oZA9y7VIgs9DN0v459r9n7BdNy4cA&s");

        assertEquals("Yogyakarta Box", box.getName());
        assertEquals("Berisi barang-barang terbaru dari Yogyakarta!",
                box.getDescription());
        assertEquals(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkETzYWWefz9GC1oZA9y7VIgs9DN0v459r9n7BdNy4cA&s",
                box.getPicture().toString());
    }

    @Test
    void testCalculateNewPrice() throws MalformedURLException {
        items.remove(2);
        box.setItems(items);
        assertEquals((int) Math.floor(140000), box.calculatePrice());
    }

    @Test
    void testCalculatePriceWithZeroItem() throws MalformedURLException {
        items.clear();
        box.setItems(items);
        assertEquals(0, box.calculatePrice());
    }

    @Test
    void testGetPictureFromURL() throws IOException {
        InputStream pictureStream = box.getPicture().openStream();
        assertNotNull(pictureStream);
    }

    @Test
    void testSetInvalidURL() {
        assertThrows(MalformedURLException.class, () -> {
            box.setPicture("invalidURL");
        });
    }
}
