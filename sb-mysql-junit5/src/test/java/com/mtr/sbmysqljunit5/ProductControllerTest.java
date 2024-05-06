package com.mtr.sbmysqljunit5;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.mtr.sbmysqljunit5.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ProductControllerTest {
	
	@Autowired
	ProductService productService;
	
	@Configuration
	static class ProductServices{
		@Bean
		ProductService  getProductService() {
			return mock(ProductService.class);
		}
		
		
	}

	
	
	MockMvc mockMvc;
	
	@Test
	public void testGetProdutById() throws Exception {
		int id = 4;
		
		ResultActions actions = mockMvc.perform(get("/api/products/"+id));
		
		
		 actions.andExpect(status().isOk());
		
	}
	

}
