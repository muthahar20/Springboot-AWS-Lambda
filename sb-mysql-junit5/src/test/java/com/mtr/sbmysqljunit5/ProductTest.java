package com.mtr.sbmysqljunit5;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void getProductTest() throws Exception {
		 mvc.perform(MockMvcRequestBuilders.get("/api/products/2").accept(MediaType.APPLICATION_JSON))
			.andExpect(status().is4xxClientError());
		 
		 
		 
		 
		 
	}
	
}
	
