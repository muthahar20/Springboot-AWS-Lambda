package com.mtr.springbootDynamoDB.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtr.springbootDynamoDB.model.Product;
import com.mtr.springbootDynamoDB.repository.DynamoDbRepository;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	private DynamoDbRepository repository;

	
	@PostMapping
	public String insertIntoDynamoDB(@RequestBody Product product) {
		repository.insertIntoDynamoDB(product);
		return "Successfully inserted into DynamoDB table";
	}
	
	@GetMapping
	public ResponseEntity<Product> getOneStudentDetails(@RequestParam String productId, @RequestParam String productName) {
		Product student = repository.getOneStudentDetails(productId, productName);
		return new ResponseEntity<Product>(student, HttpStatus.OK);
	}

	@PutMapping
	public void updateStudentDetails(@RequestBody Product product) {
		repository.updateStudentDetails(product);
	}

	@DeleteMapping(value = "{productId}/{productName}")
	public void deleteStudentDetails(@PathVariable("productId") String productId,
			@PathVariable("productName") String productName) {
		Product product = new Product();
		product.setProductId(productId);
		product.setProductName(productName);
		repository.deleteStudentDetails(product);
	}

}
