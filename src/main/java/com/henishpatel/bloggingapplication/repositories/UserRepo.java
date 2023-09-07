package com.henishpatel.bloggingapplication.repositories;

import com.henishpatel.bloggingapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameOrEmail(String username, String email);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}
