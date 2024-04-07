package com.mtr.springbootDynamoDB.model;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="Product")
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String productName;
	
	private String productDesc;
	
	private String productId;

	@DynamoDBAttribute
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@DynamoDBAttribute
	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	@DynamoDBHashKey(attributeName = "productId")
	@DynamoDBAutoGeneratedKey
	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	
	
	
	
	
	

}
