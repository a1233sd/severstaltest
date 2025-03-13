package com.fructflow.fructflow.controller;

import com.fructflow.fructflow.dbase.Supplier;
import com.fructflow.fructflow.service.SupplierService;
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

@WebMvcTest(SupplierController.class)
@ActiveProfiles("test")
public class SupplierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SupplierService supplierService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllSuppliers() throws Exception {
        List<Supplier> suppliers = Arrays.asList(new Supplier(), new Supplier());
        when(supplierService.findAllSuppliers()).thenReturn(suppliers);

        mockMvc.perform(get("/api/suppliers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(suppliers.size()));
    }

    @Test
    public void testGetSupplierById_Found() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        when(supplierService.findSupplierById(1L)).thenReturn(Optional.of(supplier));

        mockMvc.perform(get("/api/suppliers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetSupplierById_NotFound() throws Exception {
        when(supplierService.findSupplierById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/suppliers/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        when(supplierService.createSupplier(supplier)).thenReturn(supplier);

        mockMvc.perform(post("/api/suppliers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setId(1L);
        when(supplierService.updateSupplier(1L, supplier)).thenReturn(supplier);

        mockMvc.perform(put("/api/suppliers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplier)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteSupplier() throws Exception {
        mockMvc.perform(delete("/api/suppliers/1"))
                .andExpect(status().isNoContent());
    }
}
