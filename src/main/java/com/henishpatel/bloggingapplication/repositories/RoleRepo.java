package com.henishpatel.bloggingapplication.repositories;

import com.henishpatel.bloggingapplication.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(String name);
}
