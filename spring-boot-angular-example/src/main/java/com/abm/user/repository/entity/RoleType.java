package com.abm.user.repository.entity;

public enum RoleType {
	ADMIN, USER;
	
	public static RoleType getRoleTypeFromName(String roleName){
		if(roleName.toUpperCase().equals("ADMIN")){
			return RoleType.ADMIN;
		}else if(roleName.toUpperCase().equals("USER")){
			return RoleType.USER;
		}
		return null;
	}
}
