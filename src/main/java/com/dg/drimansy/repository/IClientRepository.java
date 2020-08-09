package com.dg.drimansy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dg.drimansy.model.Client;

public interface IClientRepository extends JpaRepository<Client, Long>{
	
	@Query("Select c from Client c order by c.name")
	public List<Client> findAllOrdered();
}
