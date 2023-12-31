package com.henishpatel.bloggingapplication.services;

import com.henishpatel.bloggingapplication.payload.PostDTO;
import com.henishpatel.bloggingapplication.payload.PostResponse;

import java.util.List;

public interface PostService {
	//create

	PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId);

	//update

	PostDTO updatePost(PostDTO postDTO, Integer postId);

	// delete

	void deletePost(Integer postId);

	//get all posts

	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	//get single post

	PostDTO getPostById(Integer postId);

	//get all posts by category

	PostResponse getPostsByCategory(Integer categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	//get all posts by user
	PostResponse getPostsByUser(Integer userId, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

	//search posts
	PostResponse searchPosts(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
}
