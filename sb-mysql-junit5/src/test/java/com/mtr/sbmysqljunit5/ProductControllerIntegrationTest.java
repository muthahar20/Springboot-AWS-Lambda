package com.mtr.sbmysqljunit5;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getProductsById() throws Exception {
		mockMvc.perform(get("api/products")).andExpect(status().isOk());
		
	}
	
	
	
}
