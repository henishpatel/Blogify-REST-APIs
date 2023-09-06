package com.henishpatel.bloggingapplication.repositories;

import com.henishpatel.bloggingapplication.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment,Integer> {
}
