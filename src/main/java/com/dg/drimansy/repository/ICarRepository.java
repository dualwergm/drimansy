package com.dg.drimansy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dg.drimansy.model.Car;

public interface ICarRepository extends JpaRepository<Car, Long>{
	
	@Query("Select c from Car c order by c.plate")
	public List<Car> findAllOrdered();
}
