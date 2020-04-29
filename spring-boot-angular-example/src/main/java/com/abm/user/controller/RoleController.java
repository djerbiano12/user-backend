package com.abm.user.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.abm.user.repository.entity.Role;
import com.abm.user.repository.entity.RoleType;
import com.abm.user.service.RoleService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<RoleType> getRoles() {
		return roleService.findAll().stream().map(Role::getName).collect(Collectors.toList());
	}
}
