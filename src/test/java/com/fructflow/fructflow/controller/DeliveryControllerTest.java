package com.fructflow.fructflow.controller;

import com.fructflow.fructflow.dbase.Delivery;
import com.fructflow.fructflow.service.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DeliveryController.class)
@ActiveProfiles("test")
public class DeliveryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeliveryService deliveryService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllDeliveries() throws Exception {
        List<Delivery> deliveries = Arrays.asList(new Delivery(), new Delivery());
        when(deliveryService.getAllDeliveries()).thenReturn(deliveries);

        mockMvc.perform(get("/api/deliveries"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(deliveries.size()));
    }

    @Test
    public void testGetDeliveryById_Found() throws Exception {
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        when(deliveryService.getDeliveryById(1L)).thenReturn(Optional.of(delivery));

        mockMvc.perform(get("/api/deliveries/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetDeliveryById_NotFound() throws Exception {
        when(deliveryService.getDeliveryById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/deliveries/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateDelivery() throws Exception {
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        when(deliveryService.createDelivery(delivery)).thenReturn(delivery);

        mockMvc.perform(post("/api/deliveries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(delivery)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateDelivery() throws Exception {
        Delivery delivery = new Delivery();
        delivery.setId(1L);
        when(deliveryService.updateDelivery(1L, delivery)).thenReturn(delivery);

        mockMvc.perform(put("/api/deliveries/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(delivery)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteDelivery() throws Exception {
        mockMvc.perform(delete("/api/deliveries/1"))
                .andExpect(status().isNoContent());
    }
}
