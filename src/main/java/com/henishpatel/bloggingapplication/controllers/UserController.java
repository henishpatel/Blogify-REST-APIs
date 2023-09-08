package com.henishpatel.bloggingapplication.controllers;

import com.henishpatel.bloggingapplication.entities.User;
import com.henishpatel.bloggingapplication.payload.ApiResponse;
import com.henishpatel.bloggingapplication.payload.UserDTO;
import com.henishpatel.bloggingapplication.repositories.UserRepo;
import com.henishpatel.bloggingapplication.services.UserService;
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

	public Integer getAuthenticatedUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			User user = (User) authentication.getPrincipal();
			// Assuming your user details object has a method to get the user's ID
			return user.getId(); // Adjust this based on your UserDetails implementation
		}

		return null; // If no user is authenticated or no ID is found
	}

	// POST - create user
	@PostMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO createUserDTO = userService.createUser(userDTO);
		return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
	}

	// PUT - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable Integer userId){
		UserDTO updateUserDTO = userService.updateUser(userDTO, userId);
		return ResponseEntity.ok(updateUserDTO);
	}

	// DELETE - delete user
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
	}

	// GET - get user
	@GetMapping("/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserById(@PathVariable Integer userId,Principal principal){

		if (principal != null && principal.getName() != null) {
			Integer authenticatedUserId = getAuthenticatedUserId();
			if (authenticatedUserId.equals(userId)) {
				// User is authorized to retrieve their own identity
				UserDTO userDTO = userService.getUserById(userId);
				return ResponseEntity.ok(userDTO);
			}
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("You are not allowed to access other user details",false),HttpStatus.FORBIDDEN);
	}

}
