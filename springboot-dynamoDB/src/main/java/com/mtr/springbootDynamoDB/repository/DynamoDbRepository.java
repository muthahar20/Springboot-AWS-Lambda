package com.mtr.springbootDynamoDB.repository;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.mtr.springbootDynamoDB.model.Product;

@Repository
public class DynamoDbRepository {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DynamoDbRepository.class);
	
	
	@Autowired
	private DynamoDBMapper mapper;
	
	public void insertIntoDynamoDB(Product product) {
		mapper.save(product);
	}
	
	public Product getOneStudentDetails(String productId, String productName) {
		return mapper.load(Product.class, productId, productName);
	}

	public void updateStudentDetails(Product product) {
		try {
			mapper.save(product, buildDynamoDBSaveExpression(product));
		} catch (ConditionalCheckFailedException exception) {
			LOGGER.error("invalid data - " + exception.getMessage());
		}
	}

	public void deleteStudentDetails(Product product) {
		mapper.delete(product);
	}

	public DynamoDBSaveExpression buildDynamoDBSaveExpression(Product product) {
		DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
		Map<String, ExpectedAttributeValue> expected = new HashMap<>();
		expected.put("studentId", new ExpectedAttributeValue(new AttributeValue(product.getProductId()))
				.withComparisonOperator(ComparisonOperator.EQ));
		saveExpression.setExpected(expected);
		return saveExpression;
	}

}
