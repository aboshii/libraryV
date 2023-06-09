package com.library.springlibrary.repository;

import com.library.springlibrary.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@ComponentScan("com.library.springlibrary.model")
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByNickname(String nickname);
}
