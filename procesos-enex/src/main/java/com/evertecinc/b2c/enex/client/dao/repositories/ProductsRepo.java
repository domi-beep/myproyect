package com.evertecinc.b2c.enex.client.dao.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.evertecinc.b2c.enex.client.model.entities.Products;

public interface ProductsRepo extends JpaRepository<Products, String> {

	public List<Products> findByProductStatusAndProductCodeIn(String productStatus, List<String> productCodes, Pageable pageable);
	public Long countByProductStatusAndProductCodeIn(String productStatus, List<String> productCodes);

	public List<Products> findByProductStatus(String productStatus, Pageable pageable);
	public Long countByProductStatus(String productStatus);
	
	public Products findByProductStatusAndProductCode(String productStatus, String productCode);
	
	public Products findByProductCode(String productCode);
	
}
