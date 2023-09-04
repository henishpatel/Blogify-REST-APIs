package com.henishpatel.bloggingapplication.controllers;

import com.henishpatel.bloggingapplication.entities.User;
import com.henishpatel.bloggingapplication.payload.ApiResponse;
import com.henishpatel.bloggingapplication.payload.UserDTO;
import com.henishpatel.bloggingapplication.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	// POST - create user
	@PostMapping("/")
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		UserDTO createUserDTO = userService.createUser(userDTO);
		return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
	}
	// PUT - update user
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO,@PathVariable Integer userId ){
		UserDTO updateUserDTO = userService.updateUser(userDTO,userId);
		return ResponseEntity.ok(updateUserDTO);
	}

	// DELETE - delete user
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity<>(new ApiResponse("User deleted Successfully",true),HttpStatus.OK);
	}

	// GET - get user
	@GetMapping("/")
	public ResponseEntity<List<UserDTO>> getAllUsers(){
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable Integer userId){
		return ResponseEntity.ok(userService.getUserById(userId));
	}

}
