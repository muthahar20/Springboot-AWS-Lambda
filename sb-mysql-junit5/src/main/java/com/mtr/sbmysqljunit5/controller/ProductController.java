package com.mtr.sbmysqljunit5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mtr.sbmysqljunit5.model.Product;
import com.mtr.sbmysqljunit5.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return productService.crateProduct(product);
	}

	@GetMapping()
    public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable Integer id) {
		return productService.getProductById(null);
	}
	
	
	
	@PutMapping()
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
	
	
}