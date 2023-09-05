package com.henishpatel.bloggingapplication.controllers;

import com.henishpatel.bloggingapplication.payload.ApiResponse;
import com.henishpatel.bloggingapplication.payload.PostDTO;
import com.henishpatel.bloggingapplication.payload.PostResponse;
import com.henishpatel.bloggingapplication.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId){
		PostDTO createPostDTO = postService.createPost(postDTO,userId,categoryId);
		return new ResponseEntity<PostDTO>(createPostDTO, HttpStatus.CREATED);
	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted Successfully",true),HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDTO,@PathVariable Integer postId){
		PostDTO updatePostDTO = postService.updatePost(postDTO,postId);
		return new ResponseEntity<PostDTO>(updatePostDTO,HttpStatus.OK);
	}

	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostByUserId(
			@PathVariable Integer userId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false)String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false)String sortDir
	){
		PostResponse postResponseDTOS = postService.getPostsByUser(userId, pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponseDTOS,HttpStatus.OK);
	}
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostByCategoryId(
			@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false)String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false)String sortDir
	){
		PostResponse postResponseDTOS = postService.getPostsByCategory(categoryId, pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponseDTOS,HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = "postId", required = false)String sortBy,
			@RequestParam(value = "sortDir", defaultValue = "asc", required = false)String sortDir
	){
		PostResponse postResponseDTOS = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponseDTOS,HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		PostDTO postDTO = postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDTO,HttpStatus.OK);
	}

}
