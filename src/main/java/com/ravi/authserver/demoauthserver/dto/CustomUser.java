package com.ravi.authserver.demoauthserver.dto;

import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

	private static final long serialVersionUID = 1L;
	public CustomUser(UserDTO dto) {
		super(dto.getUsername(),dto.getPassword(),dto.getGrantedAuthorities());
	}
}
