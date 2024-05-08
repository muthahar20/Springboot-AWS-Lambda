package com.mtr.sbmysqljunit5.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mtr.sbmysqljunit5.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	
	@Query(value="select * from products p where place = :location", nativeQuery = true)
	public List<Product> getProductsByLocation(String location);

}
