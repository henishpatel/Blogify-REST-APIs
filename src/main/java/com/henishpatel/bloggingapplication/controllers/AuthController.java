package com.henishpatel.bloggingapplication.controllers;

import com.henishpatel.bloggingapplication.entities.User;
import com.henishpatel.bloggingapplication.exceptions.ApiException;
import com.henishpatel.bloggingapplication.exceptions.ResourceNotFoundException;
import com.henishpatel.bloggingapplication.payload.JwtAuthRequest;
import com.henishpatel.bloggingapplication.payload.JwtAuthResponse;
import com.henishpatel.bloggingapplication.payload.UserDTO;
import com.henishpatel.bloggingapplication.repositories.UserRepo;
import com.henishpatel.bloggingapplication.security.JwtTokenHelper;
import com.henishpatel.bloggingapplication.services.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

	public String getAuthenticatedUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();// If no user is authenticated or no ID is found
	}

	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception {
		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		String token = this.jwtTokenHelper.generateToken(userDetails);

		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		response.setUser(this.mapper.map((User) userDetails, UserDTO.class));
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username,
				password);

		try {

			this.authenticationManager.authenticate(authenticationToken);

		} catch (BadCredentialsException e) {
			System.out.println("Invalid Details !!");
			throw new ApiException("Invalid username or password !!");
		}

	}

	@PostMapping("/register")
	public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
		UserDTO registeredUser = this.userService.registerNewUser(userDTO);
		return new ResponseEntity<UserDTO>(registeredUser, HttpStatus.CREATED);
	}

	@Autowired
	private ModelMapper mapper;


}
