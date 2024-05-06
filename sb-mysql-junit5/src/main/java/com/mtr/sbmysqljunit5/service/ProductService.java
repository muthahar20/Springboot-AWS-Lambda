package com.mtr.sbmysqljunit5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mtr.sbmysqljunit5.model.Product;
import com.mtr.sbmysqljunit5.repo.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	public Product crateProduct(Product p) {
		return productRepository.save(p);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product getProductById(Integer id) {
		return productRepository.findById(id).orElse(null);
				
	}
	
	public Product updateProduct( Product product) {
		return productRepository.save(product);
	}
	
	
}
