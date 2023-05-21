package com.library.springlibrary.controller.Endpoints;

import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.model.User;
import com.library.springlibrary.model.dto.UserDto;
import com.library.springlibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/api",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
class UserEndpoint {
    private UserService userService;

    @GetMapping("/users")
    List<UserDto> getBookList() {
        return userService.getUsers();
    }

    @RequestMapping("/users/{id}")
    ResponseEntity<UserDto> getUser(@PathVariable(required = false, name = "id") Long id) {
        UserDto userById;
        try {
            userById = userService.getUserDtoById(id);
        } catch (UserNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userById);
    }
    @RequestMapping("users/create")
    @ResponseStatus(HttpStatus.CREATED)
    void addUser(@RequestParam String firstName,
                @RequestParam String lastName) {
        userService.addUser(new UserDto(firstName, lastName));
    }
}

