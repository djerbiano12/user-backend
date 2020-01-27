package com.abm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abm.user.repository.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User getUserByEmail(String email);
}
