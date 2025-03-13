package com.fructflow.fructflow.controller;

import com.fructflow.fructflow.dbase.Category;
import com.fructflow.fructflow.dbase.Product;
import com.fructflow.fructflow.service.ProductService;
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

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllProducts() throws Exception {
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(products.size()));
    }

    @Test
    public void testGetProductById_Found() throws Exception {
        Product product = new Product();
        product.setId(1L);
        when(productService.getProductById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testGetProductById_NotFound() throws Exception {
        when(productService.getProductById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        when(productService.createProduct(product)).thenReturn(product);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        when(productService.updateProduct(1L, product)).thenReturn(product);

        mockMvc.perform(put("/api/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/api/products/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetProductsByCategory() throws Exception {
        Category category = Category.APPLE;
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getProductsByCategory(category)).thenReturn(products);

        mockMvc.perform(get("/api/products/category/{category}", category))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(products.size()));
    }
}
