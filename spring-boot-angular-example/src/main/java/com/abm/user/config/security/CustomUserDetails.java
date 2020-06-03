package com.abm.user.config.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.abm.user.repository.entity.Role;
import com.abm.user.repository.entity.User;

public class CustomUserDetails implements UserDetails{

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Set grantedAuthorities;
	
	public CustomUserDetails(User user) {
		this.username = user.getEmail();
		this.password = user.getPassword();
		this.grantedAuthorities = this.getAuthorities(user);
	}
	
	private Set getAuthorities(User user) {
        Set<Role> roleByUserId = user.getRoles();
        final Set authorities = roleByUserId.stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName().toString().toUpperCase())).collect(Collectors.toSet());
        return authorities;
    }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.grantedAuthorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
