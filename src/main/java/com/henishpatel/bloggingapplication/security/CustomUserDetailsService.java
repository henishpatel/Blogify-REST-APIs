package com.henishpatel.bloggingapplication.security;

import com.henishpatel.bloggingapplication.entities.User;
import com.henishpatel.bloggingapplication.exceptions.ResourceNotFoundException;
import com.henishpatel.bloggingapplication.repositories.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserRepo userRepo;

	public CustomUserDetailsService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		User user = userRepo.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
				.orElseThrow(() ->
						new ResourceNotFoundException(usernameOrEmail));
		return user;
	}
}
