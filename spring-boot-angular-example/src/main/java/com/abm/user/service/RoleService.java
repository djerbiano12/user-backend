package com.abm.user.service;

import java.util.List;

import com.abm.user.repository.entity.Role;

public interface RoleService {
	public List<Role> findAll();
	public Role getRoleByName(String name);
}
