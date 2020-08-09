package com.dg.drimansy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dg.drimansy.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long>{
	
	@Query("Select p from Product p order by p.category.name, p.name")
	public List<Product> findAllOrdered();
}
