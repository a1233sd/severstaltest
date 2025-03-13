package com.fructflow.fructflow.controller;

import com.fructflow.fructflow.dbase.PriceOffer;
import com.fructflow.fructflow.service.PriceOfferService;
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

@WebMvcTest(PriceOfferController.class)
@ActiveProfiles("test")
public class PriceOfferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceOfferService priceOfferService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllPriceOffers() throws Exception {
        List<PriceOffer> offers = Arrays.asList(new PriceOffer(), new PriceOffer());
        when(priceOfferService.getAllPriceOffers()).thenReturn(offers);

        mockMvc.perform(get("/api/price-offers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(offers.size()));
    }

    @Test
    public void testGetPriceOfferById_Found() throws Exception {
        PriceOffer offer = new PriceOffer();
        offer.setId(1L);
        when(priceOfferService.getPriceOfferById(1L)).thenReturn(Optional.of(offer));

        mockMvc.perform(get("/api/price-offers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetPriceOfferById_NotFound() throws Exception {
        when(priceOfferService.getPriceOfferById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/price-offers/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreatePriceOffer() throws Exception {
        PriceOffer offer = new PriceOffer();
        offer.setId(1L);
        when(priceOfferService.createPriceOffer(offer)).thenReturn(offer);

        mockMvc.perform(post("/api/price-offers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(offer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdatePriceOffer() throws Exception {
        PriceOffer offer = new PriceOffer();
        offer.setId(1L);
        when(priceOfferService.updatePriceOffer(1L, offer)).thenReturn(offer);

        mockMvc.perform(put("/api/price-offers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(offer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeletePriceOffer() throws Exception {
        mockMvc.perform(delete("/api/price-offers/1"))
                .andExpect(status().isNoContent());
    }
}
