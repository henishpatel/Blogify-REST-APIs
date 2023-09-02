package com.henishpatel.bloggingapplication.repositories;

import com.henishpatel.bloggingapplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {

}
