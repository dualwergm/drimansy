package com.dg.drimansy.service;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dg.drimansy.model.User;
import com.dg.drimansy.repository.IUserRepository;

@Service
public class UserService {
	private final IUserRepository userRepository;
	
	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional(readOnly = true)
	public long getUserId() {
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		List<User> findByUserl = this.userRepository.findByUser(user);
		return findByUserl.get(0).getId().longValue();
	}
}
