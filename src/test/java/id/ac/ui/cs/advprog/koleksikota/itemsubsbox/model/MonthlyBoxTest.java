package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonthlyBoxTest {
    ArrayList<Item> items;
    Box monthlyBox;

    @BeforeEach
    void setup() throws MalformedURLException {
        items = new ArrayList<>();

        Item item1 = new Item();
        item1.setItemId("75d36b9a-3a36-4661-816e-4789aeb9d69a");
        item1.setName("Kemeja Batik Motif Sidoluhur");
        item1.setDescription("Batik khas Yogyakarta");
        item1.setPicture("https://batiks128.com/wp-content/uploads/products_img/LP6653BTF-M-300-sidoluhur.jpg");
        item1.setPrice(100000);
        items.add(item1);

        Item item2 = new Item();
        item2.setItemId("97cf9e70-60b2-400c-906e-ee4a76d81ad0");
        item2.setName("Bakpia Pathok 25");
        item2.setDescription("100% Asli Yogyakarta");
        item2.setPicture("https://img.ws.mms.shopee.co.id/654fdb47f82ec541eca2acb06f5fb820");
        item2.setPrice(40000);
        items.add(item2);

        monthlyBox = new MonthlyBox();
        monthlyBox.setBoxId("2d43f130-4305-4c9f-a87e-92b6b6ef1619");
        monthlyBox.setName("Box Yogyakarta");
        monthlyBox.setDescription("Berisi barang-barang unik dari Yogyakarta");
        monthlyBox.setPicture(
                "https://terasmalioboro.jogjaprov.go.id/wp-content/uploads/2022/08/yogyakarta-monument.jpg");
        monthlyBox.setItems(items);
    }

    @AfterEach
    void cleanup() {
        items.clear();
    }

    @Test
    void testCreateBox() {
        assertEquals("2d43f130-4305-4c9f-a87e-92b6b6ef1619", monthlyBox.getBoxId());
        assertEquals("MTH - Box Yogyakarta", monthlyBox.getName());
        assertEquals("Berisi barang-barang unik dari Yogyakarta", monthlyBox.getDescription());
        assertEquals("https://terasmalioboro.jogjaprov.go.id/wp-content/uploads/2022/08/yogyakarta-monument.jpg",
                monthlyBox.getPicture().toString());
        assertEquals((int) Math.floor(140000 * 1.05), monthlyBox.calculatePrice());
        assertArrayEquals(items.toArray(), monthlyBox.getItems().toArray());
    }

    @Test
    void testEditBox() throws MalformedURLException {
        monthlyBox.setName("Yogyakarta Box");
        monthlyBox.setPicture(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkETzYWWefz9GC1oZA9y7VIgs9DN0v459r9n7BdNy4cA&s");

        assertEquals("MTH - Yogyakarta Box", monthlyBox.getName());
        assertEquals(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkETzYWWefz9GC1oZA9y7VIgs9DN0v459r9n7BdNy4cA&s",
                monthlyBox.getPicture().toString());
        assertEquals((int) Math.floor(140000 * 1.05), monthlyBox.calculatePrice());
    }

    @Test
    void testCalculateNewPrice() throws MalformedURLException {
        items.remove(1);
        monthlyBox.setItems(items);
        assertEquals((int) Math.floor(100000 * 1.05), monthlyBox.calculatePrice());
    }

    @Test
    void testCalculatePriceWithZeroItem() throws MalformedURLException {
        items.clear();
        ;
        monthlyBox.setItems(items);
        assertEquals(0, monthlyBox.calculatePrice());
    }

    @Test
    void testSetInvalidURL() {
        assertThrows(MalformedURLException.class, () -> {
            monthlyBox.setPicture("invalidURL");
            ;
        });
    }

    @Test
    void testAccessPictureFromURL() throws IOException {
        InputStream pictureStream = monthlyBox.getPicture().openStream();
        assertNotNull(pictureStream);
    }
}
