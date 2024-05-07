package com.mtr.sbmysqljunit5.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.mtr.sbmysqljunit5.model.Product;
import com.mtr.sbmysqljunit5.repo.ProductRepository;

@SpringBootTest
class ProductServiceTest {

	@Autowired
	ProductService productService;

	@MockBean
	ProductRepository productRepository;
	
	@MockBean
	Product mockProduct;

	@BeforeEach
	void setup() {
		Optional<Product> product =Optional.of(new Product(11, "iPhone8", "Delaware", "4G"));
		Mockito.when(productRepository.findById(11)).thenReturn(product);
		//Mockito.when(productService.crateProduct(mockProduct)).thenReturn(product);
	}

	@Test
	public void testGetProductById() {
		String productName = "iPhone8";
		Product productById = productService.getProductById(11);
		assertEquals(productById.getName(), productName);

	}
	
	@Test
	public void testGetProducts() {
		 List<Product> products = Arrays.asList(new Product(), new Product());
		  when(productRepository.findAll()).thenReturn(products);
		  List<Product> result = productService.getAllProducts();
		  assertEquals(products.size(), result.size());

	}
	
	 @Test
	    public void testCreateProduct() {
		 
		  Product product = new Product();
		  when(productRepository.save(product)).thenReturn(product);
		  Product result = productService.crateProduct(product);
		  assertEquals(product, result);
		 
	 }
	 
	 @Test
	    public void testGetProductByIdNotFound() {
		 int id =10;
		 when(productRepository.findById(id)).thenReturn(Optional.empty());
		 Product result = productService.getProductById(id);
		 assertEquals(null, result);
	 }
	
	 @Test
	    public void testUpdateProduct() {
		 Product product = new Product();
		  when(productRepository.save(product)).thenReturn(product);	
		  Product result = productService.updateProduct(product);
		  assertEquals(product, result);
	 }
	
	
	
	
	
}
