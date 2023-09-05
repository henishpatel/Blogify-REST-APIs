package com.henishpatel.bloggingapplication.services.impl;

import com.henishpatel.bloggingapplication.entities.User;
import com.henishpatel.bloggingapplication.exceptions.ResourceNotFoundException;
import com.henishpatel.bloggingapplication.payload.UserDTO;
import com.henishpatel.bloggingapplication.repositories.UserRepo;
import com.henishpatel.bloggingapplication.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTO createUser(UserDTO userDTO) {
		User user = dtoToUser(userDTO);
		User savedUser = userRepo.save(user);
		return userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDTO, Integer userId) {

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", " Id ", userId));

		user.setName(userDTO.getName());
		user.setEmail(userDTO.getEmail());
		user.setPassword(userDTO.getPassword());
		user.setAbout(userDTO.getAbout());

		User updatedUser = userRepo.save(user);
		return userToDto(updatedUser);
	}

	@Override
	public UserDTO getUserById(Integer userId) {

		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
		return userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<User> users = userRepo.findAll();
		return users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User","Id",userId));
		userRepo.delete(user);
	}

	public User dtoToUser(UserDTO userDTO) {

		User user = modelMapper.map(userDTO,User.class);

//		user.setId(userDTO.getId());
//		user.setName(userDTO.getName());
//		user.setEmail(userDTO.getEmail());
//		user.setAbout(userDTO.getAbout());
//		user.setPassword(userDTO.getPassword());

		return user;
	}

	public UserDTO userToDto(User user) {

		UserDTO userDTO = modelMapper.map(user,UserDTO.class);

//		userDTO.setId(user.getId());
//		userDTO.setName(user.getName());
//		userDTO.setEmail(user.getEmail());
//		userDTO.setAbout(user.getAbout());
//		userDTO.setPassword(user.getPassword());
		return userDTO;
	}
}
