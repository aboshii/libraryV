package com.library.springlibrary.repository;

import com.library.springlibrary.model.User;
import com.library.springlibrary.model.UserRole;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

@ComponentScan("com.library.springlibrary.model")
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    Optional<UserRole> findByName(String nickname);
}
