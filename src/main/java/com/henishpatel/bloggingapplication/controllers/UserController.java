package com.henishpatel.bloggingapplication.controllers;

import com.henishpatel.bloggingapplication.entities.User;
import com.henishpatel.bloggingapplication.payload.ApiResponse;
import com.henishpatel.bloggingapplication.payload.UserDTO;
import com.henishpatel.bloggingapplication.repositories.UserRepo;
import com.henishpatel.bloggingapplication.security.JwtTokenHelper;
import com.henishpatel.bloggingapplication.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepo userRepo;

	public String getAuthenticatedUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();// If no user is authenticated or no ID is found
	}

	// POST - create user
	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO createUserDTO = userService.createUser(userDTO);
		return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
	}

	// PUT - update user
	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@PutMapping("/{userId}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable Integer userId){

		// Check if the username in the JWT token matches the provided UserDTO username
		if (!getAuthenticatedUserName().equals(userDTO.getUsername())) {
			return new ResponseEntity<>(new ApiResponse("You are not allowed to update any other",true),HttpStatus.OK);
		}

		UserDTO updateUserDTO = userService.updateUser(userDTO, userId);
		return ResponseEntity.ok(updateUserDTO);
	}

	// DELETE - delete user
	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
	}

	// GET - get user
	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@GetMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getUserById(@PathVariable Integer userId) {

		// Retrieve the user's data by userId
		UserDTO userDTO = userService.getUserById(userId);
		return ResponseEntity.ok(userDTO);
	}
}
