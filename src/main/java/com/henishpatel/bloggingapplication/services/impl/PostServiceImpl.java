package com.henishpatel.bloggingapplication.services.impl;

import com.henishpatel.bloggingapplication.entities.Category;
import com.henishpatel.bloggingapplication.entities.Post;
import com.henishpatel.bloggingapplication.entities.User;
import com.henishpatel.bloggingapplication.exceptions.ResourceNotFoundException;
import com.henishpatel.bloggingapplication.payload.PostDTO;
import com.henishpatel.bloggingapplication.repositories.CategoryRepo;
import com.henishpatel.bloggingapplication.repositories.PostRepo;
import com.henishpatel.bloggingapplication.repositories.UserRepo;
import com.henishpatel.bloggingapplication.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private CategoryRepo categoryRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
		Post post = modelMapper.map(postDTO, Post.class);

		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","User ID",userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category","Category ID",categoryId));

		post.setImageName("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);

		Post createdPost = postRepo.save(post);
		return modelMapper.map(createdPost,PostDTO.class);
	}

	@Override
	public PostDTO updatePost(PostDTO postDTO, Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","Post ID",postId));
		post.setTitle(postDTO.getTitle());
		post.setContent(postDTO.getContent());
		Post updatedPost = postRepo.save(post);
		return modelMapper.map(updatedPost,PostDTO.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","Post ID",postId));
		postRepo.delete(post);
	}

	@Override
	public List<PostDTO> getAllPost() {
		List<Post> posts = postRepo.findAll();
		return posts.stream().map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());
	}

	@Override
	public PostDTO getPostById(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post","Post ID",postId));
		return modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getPostsByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category","Category ID",categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		return posts.stream().map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

	}

	@Override
	public List<PostDTO> getPostsByUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User","User ID",userId));
		List<Post> posts = postRepo.findByUser(user);
		return posts.stream().map((post)-> modelMapper.map(post, PostDTO.class)).collect(Collectors.toList());

	}

	@Override
	public List<PostDTO> searchPosts(String keyword) {
		return null;
	}
}
