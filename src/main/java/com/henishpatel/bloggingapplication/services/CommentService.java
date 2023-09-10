package com.henishpatel.bloggingapplication.services;


import com.henishpatel.bloggingapplication.entities.User;
import com.henishpatel.bloggingapplication.payload.CommentDTO;


public interface CommentService {

	CommentDTO createComment(CommentDTO commentDTO, Integer postId);

	void deleteCommentById(Integer commentId);

}
