package com.mtr.sbmysqljunit5.controller;

import com.mtr.sbmysqljunit5.model.Product;
import com.mtr.sbmysqljunit5.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductService productService;

    private Product product1;
    private Product product2;
    private List<Product> productList;

    @BeforeEach
    void setUp() {
        product1 = new Product(1, "Product 1", "Location A", "Type A");
        product2 = new Product(2, "Product 2", "Location B", "Type B");

        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
    }

    @Test
    public void getAllProducts() throws Exception {

        Mockito.when(productService.getAllProducts()).thenReturn(productList);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2));

      /*
        // Get All products
        MvcResult result = mockMvc.perform(get("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                // Print the response
                .andDo(print())
                // Check the status is 200
                .andExpect(status().isOk())
                .andReturn();

        // Get the response as a String
        String resultBody = result.getResponse().getContentAsString();
        // Assert the String is not null
        assertNotNull(resultBody);
        // Continue to assert the data using Matchers

       */
    }


    @Test
    public void createProductTest() throws Exception {
        Product newProduct = new Product(3, "New Product", "Location C", "Type C");
        Mockito.when(productService.crateProduct(Mockito.any(Product.class))).thenReturn(newProduct);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"New Product\", \"location\": \"Location C\", \"type\": \"Type C\" }"))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Product"));
    }



    @AfterEach
    void tearDown() {
    }



    @Test
    void getProductById() throws Exception {
        Mockito.when(productService.getProductById(1)).thenReturn(product1);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print());
               // .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
              //  .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 1"));
    }

    @Test
    void updateProduct() throws Exception {
        Product updatedProduct = new Product(1, "Updated Product", "Updated Location", "Updated Type");
        Mockito.when(productService.updateProduct(Mockito.any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 1, \"name\": \"Updated Product\", \"location\": \"Updated Location\", \"type\": \"Updated Type\" }"))
                .andExpect(MockMvcResultMatchers.status().isOk());
             //   .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Product"))
             //   .andExpect(MockMvcResultMatchers.jsonPath("$.location").value("Updated Location"));
    }

    @Test
    public void getProductsByLocationTest() throws Exception {
        String location = "Location A";
        Mockito.when(productService.getProductsByLocation(location)).thenReturn(List.of(product1));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/location/" + location))
                .andExpect(MockMvcResultMatchers.status().isOk());
               // .andExpect(MockMvcResultMatchers.jsonPath("$[0].location").value(location));
    }
}