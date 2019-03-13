package com.ravi.authserver.demoauthserver.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class UserDTO {
	private String username;
	private String password;
	private Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password; 
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Collection<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}
	public void setGrantedAuthorities(Collection<GrantedAuthority> grantedAuthorities) {
		this.grantedAuthorities = grantedAuthorities;
	}
	
}
