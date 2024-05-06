package com.mtr.sbmysqljunit5.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mtr.sbmysqljunit5.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	

}
