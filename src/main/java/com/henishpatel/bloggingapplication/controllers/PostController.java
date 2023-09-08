package com.henishpatel.bloggingapplication.controllers;

import com.henishpatel.bloggingapplication.config.AppConstants;
import com.henishpatel.bloggingapplication.entities.Post;
import com.henishpatel.bloggingapplication.payload.ApiResponse;
import com.henishpatel.bloggingapplication.payload.PostDTO;
import com.henishpatel.bloggingapplication.payload.PostResponse;
import com.henishpatel.bloggingapplication.services.FileService;
import com.henishpatel.bloggingapplication.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class PostController {

	@Autowired
	private PostService postService;

	@Autowired
	private FileService fileService;

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${project.image}")
	private String path;

//	@PostMapping("/user/{userId}/category/{categoryId}/posts")
//	public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable Integer userId, @PathVariable Integer categoryId){
//		PostDTO createPostDTO = postService.createPost(postDTO,userId,categoryId);
//		return new ResponseEntity<PostDTO>(createPostDTO, HttpStatus.CREATED);
//	}

	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Post deleted Successfully",true),HttpStatus.OK);
	}

//	@PutMapping("/posts/{postId}")
//	public ResponseEntity<PostDTO> updatePostById(@RequestBody PostDTO postDTO,@PathVariable Integer postId){
//		PostDTO updatePostDTO = postService.updatePost(postDTO,postId);
//		return new ResponseEntity<PostDTO>(updatePostDTO,HttpStatus.OK);
//	}

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
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false)String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false)String sortDir
	){
		PostResponse postResponseDTOS = postService.getPostsByCategory(categoryId, pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponseDTOS,HttpStatus.OK);
	}

	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false)String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false)String sortDir
	){
		PostResponse postResponseDTOS = postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postResponseDTOS,HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDTO> getPostById(@PathVariable Integer postId){
		PostDTO postDTO = postService.getPostById(postId);
		return new ResponseEntity<PostDTO>(postDTO,HttpStatus.OK);
	}

	@GetMapping("/posts/search/{searchKeyword}")
	public ResponseEntity<PostResponse> getPostsByKeyword(
			@PathVariable String searchKeyword,
			@RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false)String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.SORT_DIR, required = false)String sortDir
	){
		PostResponse postsDTOs = postService.searchPosts(searchKeyword,pageNumber, pageSize, sortBy, sortDir);
		return new ResponseEntity<PostResponse>(postsDTOs,HttpStatus.OK);
	}

	// Post Image Upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDTO> uploadImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
	) throws IOException {

		PostDTO postDTO = postService.getPostById(postId);

		String fileName = this.fileService.uploadImage(path, image);
		postDTO.setImageName(fileName);
		PostDTO updatePost = this.postService.updatePost(postDTO, postId);
		return new ResponseEntity<PostDTO>(updatePost, HttpStatus.OK);
	}

	//Get image
	@GetMapping(value = "/post/image/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
			HttpServletResponse response
	) throws IOException {

		InputStream resource = this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream())   ;

	}
//
	@PostMapping(value = "/user/{userId}/category/{categoryId}/posts", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<PostDTO> createPostWithImage(
//			@RequestBody PostDTO postDTO, // Use @RequestPart for the JSON body
			@RequestPart("postDTO") String postDTOString,
			@RequestPart("image") MultipartFile image, // Use @RequestPart for the image
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
	) throws IOException {
//		Handle image upload
		String fileName = this.fileService.uploadImage(path, image);

		PostDTO postDTO = objectMapper.readValue(postDTOString, PostDTO.class);

		postDTO.setImageName(fileName);

		// Create the post
		PostDTO createPostDTO = postService.createPost(postDTO, userId, categoryId);

		return new ResponseEntity<>(createPostDTO, HttpStatus.CREATED);
	}

	@PutMapping(value ="/posts/{postId}",consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<PostDTO> updatePostById(
			@RequestPart("postDTO") String postDTOString,
			@RequestPart("image") MultipartFile image,
			@PathVariable Integer postId
	) throws IOException{
		//Handle image upload
		String fileName = this.fileService.uploadImage(path, image);
		PostDTO postDTO = objectMapper.readValue(postDTOString, PostDTO.class);

		postDTO.setImageName(fileName);
		System.out.println(postDTO);
		PostDTO updatePostDTO = postService.updatePost(postDTO,postId);
		System.out.println(updatePostDTO);
		return new ResponseEntity<PostDTO>(updatePostDTO,HttpStatus.OK);
	}

}
