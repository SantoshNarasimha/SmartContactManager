package com.api.demo.configuaration;

import java.util.Collection;
import java.util.List;


import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.api.demo.entities.User;



public class CustomUserDetails implements UserDetails{

	private User user;
	

	public CustomUserDetails(User user) {
		super();
		this.user = user;
	}
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
	
}
