package com.dg.drimansy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dg.drimansy.model.Collaborator;

public interface ICollaboratorRepository extends JpaRepository<Collaborator, Long>{
	
	@Query("Select c from Collaborator c order by c.name")
	public List<Collaborator> findAllOrdered();
}
