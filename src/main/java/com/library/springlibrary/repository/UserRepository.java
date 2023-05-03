package com.library.springlibrary.repository;

import com.library.springlibrary.model.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

@ComponentScan("com.library.springlibrary.model")
public interface UserRepository extends CrudRepository<User, Long> {
}
