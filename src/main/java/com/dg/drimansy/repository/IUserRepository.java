package com.dg.drimansy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dg.drimansy.model.User;

public interface IUserRepository extends JpaRepository<User, Long>{
	List<User> findByUser(String user);
}
