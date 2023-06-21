package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.UserCantBeAddedException;
import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.model.dto.UserDto;
import com.library.springlibrary.model.dto.UserRegisterDto;
import com.library.springlibrary.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml"
)
public class UserServiceTest_D {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookService bookService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_get_UserNotFound_exception_after_removing_user_from_db() {
        //given
        long param = 1L;
        //when
        userService.deleteUserById(param);
        UserNotFoundException thrown = assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserById(param),
                "Expected \"User not found\" but something went wrong"
        );
        //then
        assertTrue(thrown.getMessage().contains("User not found"));
    }

}
