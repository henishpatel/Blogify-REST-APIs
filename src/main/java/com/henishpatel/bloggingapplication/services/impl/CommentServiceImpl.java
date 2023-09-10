package com.henishpatel.bloggingapplication.services.impl;


import com.henishpatel.bloggingapplication.entities.Comment;
import com.henishpatel.bloggingapplication.entities.Post;
import com.henishpatel.bloggingapplication.entities.User;
import com.henishpatel.bloggingapplication.exceptions.ResourceNotFoundException;
import com.henishpatel.bloggingapplication.payload.CommentDTO;
import com.henishpatel.bloggingapplication.repositories.CommentRepo;
import com.henishpatel.bloggingapplication.repositories.PostRepo;
import com.henishpatel.bloggingapplication.repositories.UserRepo;
import com.henishpatel.bloggingapplication.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private CommentRepo commentRepo;

	@Autowired
	private UserRepo userRepo;


	@Autowired
	private ModelMapper modelMapper;

	public String getAuthenticatedUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();// If no user is authenticated or no ID is found
	}

	@Override
	public CommentDTO createComment(CommentDTO commentDTO, Integer postId) {
		User user = userRepo.findByUsername(getAuthenticatedUserName())
				.orElseThrow(() -> new ResourceNotFoundException("You are not logged In"));

		Post post = this.postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id ", postId));

		Comment comment = this.modelMapper.map(commentDTO, Comment.class);

		comment.setPost(post);

		comment.setUser(user);

		Comment savedComment = this.commentRepo.save(comment);

		return this.modelMapper.map(savedComment, CommentDTO.class);
	}

	@Override
	public void deleteCommentById(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "CommentId", commentId));
		if (!comment.getUser().getUsername().equals(getAuthenticatedUserName())) {
			throw new ResourceNotFoundException("You are not authorized to delete this comment.");
		}
		this.commentRepo.delete(comment);
	}
}
