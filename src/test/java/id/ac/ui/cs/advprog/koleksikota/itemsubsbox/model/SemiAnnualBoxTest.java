package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SemiAnnualBoxTest {
    ArrayList<Item> items;
    Box semiAnnualBox;

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

        Item item4 = new Item();
        item4.setId("79659c28-0a12-41d4-ac1f-210cb8b725b3");
        item4.setName("Kerajinan Perak Silver Craft Pedati");
        item4.setDescription("Kain penutup kepala tradisional");
        item4.setPicture(
                "https://images.tokopedia.net/img/cache/700/product-1/2020/7/16/6162601/6162601_4a52882d-1eb6-4df8-9c74-381184237d94_2048_2048.jpg");
        item4.setPrice(220000);
        items.add(item4);

        semiAnnualBox = new SemiAnnualBox();
        semiAnnualBox.setBoxId("7c59dda6-dec6-4694-8ed2-34d14db3c610");
        semiAnnualBox.setName("Box Yogyakarta");
        semiAnnualBox
                .setDescription("Dikirimkan setiap 6 bulan sekali!");
        semiAnnualBox.setPicture(
                "https://terasmalioboro.jogjaprov.go.id/wp-content/uploads/2022/08/yogyakarta-monument.jpg");
        semiAnnualBox.setItems(items);
    }

    @AfterEach
    void cleanup() {
        items.clear();
    }

    @Test
    void testCreateBox() {
        assertEquals("7c59dda6-dec6-4694-8ed2-34d14db3c610", semiAnnualBox.getBoxId());
        assertEquals("SAA - Box Yogyakarta", semiAnnualBox.getName());
        assertEquals("Dikirimkan setiap 6 bulan sekali!",
                semiAnnualBox.getDescription());
        assertEquals("https://terasmalioboro.jogjaprov.go.id/wp-content/uploads/2022/08/yogyakarta-monument.jpg",
                semiAnnualBox.getPicture().toString());
        assertEquals((int) Math.floor(420000 * 1.15), semiAnnualBox.calculatePrice());
        assertArrayEquals(items.toArray(), semiAnnualBox.getItems().toArray());
    }

    @Test
    void testEditBox() throws MalformedURLException {
        semiAnnualBox.setName("Yogyakarta Box");
        semiAnnualBox.setPicture(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkETzYWWefz9GC1oZA9y7VIgs9DN0v459r9n7BdNy4cA&s");

        assertEquals("SAA - Yogyakarta Box", semiAnnualBox.getName());
        assertEquals(
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQkETzYWWefz9GC1oZA9y7VIgs9DN0v459r9n7BdNy4cA&s",
                semiAnnualBox.getPicture().toString());
        assertEquals((int) Math.floor(420000 * 1.15), semiAnnualBox.calculatePrice());
    }

    @Test
    void testCalculateNewPrice() throws MalformedURLException {
        items.remove(2);
        semiAnnualBox.setItems(items);
        assertEquals((int) Math.floor(360000 * 1.15), semiAnnualBox.calculatePrice());
    }

    @Test
    void testCalculatePriceWithZeroItem() throws MalformedURLException {
        items.clear();
        semiAnnualBox.setItems(items);
        assertEquals(0, semiAnnualBox.calculatePrice());
    }

    @Test
    void testSetInvalidURL() {
        assertThrows(MalformedURLException.class, () -> {
            semiAnnualBox.setPicture("invalidURL");
        });
    }

    @Test
    void testAccessPictureFromURL() throws IOException {
        InputStream pictureStream = semiAnnualBox.getPicture().openStream();
        assertNotNull(pictureStream);
    }
}
