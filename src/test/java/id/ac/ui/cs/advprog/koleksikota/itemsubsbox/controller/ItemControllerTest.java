package id.ac.ui.cs.advprog.koleksikota.itemsubsbox.controller;

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

import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.model.Item;
import id.ac.ui.cs.advprog.koleksikota.itemsubsbox.service.ItemServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ItemControllerTest {
    MockMvc mockMvc;
    ObjectMapper objectMapper;
    List<Item> items;

    @Mock
    ItemServiceImpl itemService;
    @InjectMocks
    ItemController itemController;

    @BeforeEach
    void setup() throws MalformedURLException {
        mockMvc = MockMvcBuilders.standaloneSetup(itemController).build();
        objectMapper = new ObjectMapper();

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

    @Test
    void testCreateItem() throws Exception {
        Item item1 = items.get(0);
        doReturn(item1).when(itemService).create(any(Item.class));

        MvcResult mvcResult = mockMvc.perform(post("/item/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item1)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testFindAll() throws Exception {
        doReturn(items).when(itemService).findAll();

        MvcResult mvcResult = mockMvc.perform(get("/item/list")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testFindByIdFound() throws Exception {
        Item item1 = items.get(0);
        doReturn(item1).when(itemService).findById(item1.getId());

        MvcResult mvcResult = mockMvc.perform(get("/item/{itemId}", item1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        doThrow(NoSuchElementException.class).when(itemService).findById("invalidId");

        MvcResult mvcResult = mockMvc.perform(get("/item/{itemId}", "invalidId")
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testEditItem() throws Exception {
        Item item1 = items.get(0);
        Item item2 = items.get(1);
        item2.setId(item1.getId());

        doReturn(item2).when(itemService).edit(eq(item1.getId()), any(Item.class));

        MvcResult mvcResult = mockMvc.perform(post("/item/edit/{itemId}", item1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(item2)))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void testDeleteById() throws Exception {
        Item item1 = items.get(0);

        MvcResult mvcResult = mockMvc.perform(post("/item/delete/{itemId}", item1.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        mvcResult.getAsyncResult();
        assertEquals(200, mvcResult.getResponse().getStatus());
    }
}
