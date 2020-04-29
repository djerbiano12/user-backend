package com.abm.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abm.user.repository.entity.Role;
import com.abm.user.repository.entity.RoleType;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role getRoleByName(RoleType roleName);
}
