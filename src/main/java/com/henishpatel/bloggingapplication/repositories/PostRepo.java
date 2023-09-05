package com.henishpatel.bloggingapplication.repositories;

import com.henishpatel.bloggingapplication.entities.Category;
import com.henishpatel.bloggingapplication.entities.Post;
import com.henishpatel.bloggingapplication.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {

	List<Post> findByUser(User user);
	List<Post> findByCategory(Category category);

	Page<Post> findByCategory(Category category, Pageable pageable);

	Page<Post> findByUser(User user, Pageable pageable);

}
