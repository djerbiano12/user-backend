package com.abm.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abm.user.repository.RoleRepository;
import com.abm.user.repository.entity.Role;
import com.abm.user.repository.entity.RoleType;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleRepository repository;

	@Override
	public List<Role> findAll() {
		return repository.findAll();
	}

	@Override
	public Role getRoleByName(String name) {
		return repository.getRoleByName(RoleType.getRoleTypeFromName(name));
	}

}
