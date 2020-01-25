package com.mydevgeek;

import java.util.List;

public interface UserService {
	public User create(User user);
	public User delete(Long id);
	public List<?> findAll();
	public User findById(Long id);
	public User update(User user);
}
