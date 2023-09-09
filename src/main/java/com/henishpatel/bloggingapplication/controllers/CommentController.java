package com.henishpatel.bloggingapplication.controllers;

import com.henishpatel.bloggingapplication.payload.ApiResponse;
import com.henishpatel.bloggingapplication.payload.CommentDTO;
import com.henishpatel.bloggingapplication.services.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/")
public class CommentController {

	@Autowired
	private CommentService commentService;

	public String getAuthenticatedUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();// If no user is authenticated or no ID is found
	}

	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@PostMapping("/post/{postId}/comments")
	public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment, @PathVariable Integer postId) {

		CommentDTO createComment = this.commentService.createComment(comment, postId);
		return new ResponseEntity<CommentDTO>(createComment, HttpStatus.CREATED);
	}

	@SecurityRequirement(
			name = "Bearer Authentication"
	)
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {

		this.commentService.deleteCommentById(commentId);

		return new ResponseEntity<ApiResponse>(new ApiResponse("Comment deleted successfully !!", true), HttpStatus.OK);
	}
}
