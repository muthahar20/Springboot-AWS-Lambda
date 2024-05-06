package com.mtr.sbmysqljunit5;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mtr.sbmysqljunit5.controller.ProductController;
import com.mtr.sbmysqljunit5.model.Product;
import com.mtr.sbmysqljunit5.service.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTests {

	@MockBean
	private ProductService productService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	

	@Test
	public void testCreateProduct() throws Exception {
		Product product = new Product(10, "iPhone", "Delaware", "3G");
		
		mockMvc.perform(post("/api/products").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isCreated())
				.andDo(print());
				
		
		
		
	}
	
	
	
	
	

}
