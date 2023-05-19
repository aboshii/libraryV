package com.library.springlibrary.controller;

import com.library.springlibrary.model.User;
import com.library.springlibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
class UserEndpoint {
    @Autowired
    private final UserService userService;

    @GetMapping("/users")
    List<User> getBookList() {
        return userService.getUsers();
    }

    @RequestMapping("/users/{id}")
    User getUser(@PathVariable(required = false, name = "id") Long id) {
        return userService.getUserById(id);
    }
}

