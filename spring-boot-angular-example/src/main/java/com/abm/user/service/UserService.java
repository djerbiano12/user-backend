package com.abm.user.service;

import java.util.List;

import com.abm.user.repository.entity.User;

public interface UserService {
	public User create(User user);
	public User delete(Long id);
	public List<?> findAll();
	public User findById(Long id);
	public User update(User user);
	public Boolean canUserConnect(String email, String password);
}
