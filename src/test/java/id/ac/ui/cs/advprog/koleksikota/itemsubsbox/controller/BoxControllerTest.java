package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Box;
import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;
import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service.BoxServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ExtendWith(MockitoExtension.class)
public class BoxControllerTest {
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    List<Box> boxes;

    @Mock
    BoxServiceImpl boxService;
    @InjectMocks
    BoxController boxController;

    @BeforeEach
    void setup() throws MalformedURLException {
        mockMvc = MockMvcBuilders.standaloneSetup(boxController).build();
        objectMapper = new ObjectMapper();

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
    void testCreateBox() throws Exception {
        Box box1 = boxes.get(0);
        doReturn(box1).when(boxService).create(any(Box.class));

        MvcResult mvcResult = mockMvc.perform(post("/box/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(box1)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testFindAll() throws Exception {
        doReturn(boxes).when(boxService).findAll();

        MvcResult mvcResult = mockMvc.perform(get("/box/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testFindByIdFound() throws Exception {
        Box box1 = boxes.get(0);
        doReturn(box1).when(boxService).findById(box1.getId());

        MvcResult mvcResult = mockMvc.perform(get("/box/{boxId}", box1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        doThrow(NoSuchElementException.class).when(boxService).findById("invalidId");

        MvcResult mvcResult = mockMvc.perform(get("/box/{boxId}", "invalidId")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testEditBox() throws Exception {
        Box box1 = boxes.get(0);
        Box box2 = boxes.get(1);
        box2.setId(box1.getId());

        doReturn(box2).when(boxService).edit(eq(box1.getId()), any(Box.class));

        MvcResult mvcResult = mockMvc.perform(post("/box/edit/{boxId}", box1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(box2)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testDeleteById() throws Exception {
        Box box1 = boxes.get(0);

        MvcResult mvcResult = mockMvc.perform(post("/box/delete/{boxId}", box1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
