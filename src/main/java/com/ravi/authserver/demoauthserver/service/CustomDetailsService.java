package com.ravi.authserver.demoauthserver.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ravi.authserver.demoauthserver.dto.CustomUser;
import com.ravi.authserver.demoauthserver.dto.UserDTO;

@Service
public class CustomDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if(username.equals("raviprasad@gmail.com")) {
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername("raviprasasd");
			userDTO.setPassword("$2a$08$fL7u5xcvsZl78su29x1ti.dxI.9rYO8t0q5wk2ROJ.1cdR53bmaVG"); //"password"
			GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_SYSTEMADMIN");
			authorities.add(authority);
			userDTO.setGrantedAuthorities(authorities);
			CustomUser customUser = new CustomUser(userDTO);
			return customUser;
		}
		return null;
	}

}
