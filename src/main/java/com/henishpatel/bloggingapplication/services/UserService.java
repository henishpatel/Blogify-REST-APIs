package com.henishpatel.bloggingapplication.services;

import com.henishpatel.bloggingapplication.payload.UserDTO;

import java.util.List;

public interface UserService {

	UserDTO createUser(UserDTO userDTO);
	UserDTO updateUser(UserDTO userDTO, Integer userId);
	UserDTO getUserById(Integer userId);
	List<UserDTO> getAllUsers();
	void deleteUser(Integer userId);

}
