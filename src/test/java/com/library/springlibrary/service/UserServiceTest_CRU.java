package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.UserCantBeAddedException;
import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.model.UserRole;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-test.yml"
)
public class UserServiceTest_CRU {
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
    void should_add_user() {
        //given
        Long param = 3L;
        UserDto userDto = getExampleUserDtoData();
        userDto.setId(param);
        //when
        userService.addUser(userDto);
        //then
        assertEquals(userDto.getNickname(), userRepository.findById(param).get().getNickname());
    }

    @Test
    void should_try_to_add_user_but_validator_prints_error() {
        //given
        Long param = 4L;
        UserDto userDto = getExampleUserDtoData();
        userDto.setId(param);
        userDto.setNickname("abc");
        //when
        UserCantBeAddedException thrown = assertThrows(
                UserCantBeAddedException.class,
                () -> userService.addUser(userDto),
                "Expected \"User cannot be added\" but something went wrong"
        );
        //then
        assertTrue(thrown.getMessage().contains("User cannot be added"));
    }

    @Test
    void should_register_user() {
        //given
        UserRegisterDto userRegisterDto = getExampleUserRegisterDtoData();
        String param = userRegisterDto.getNickname();
        //when
        userService.registerUser(userRegisterDto);
//        for (UserDto user : userService.getUsers()) {
//            System.out.println(user.getNickname() + " " + user.getId());
//        }
        //then
        assertEquals(userRegisterDto.getEmail(),
                userRepository.findUserByNickname(param).get().getEmail());
    }

    @Test
    void should_get_userDto_with_id_1L() {
        //given
        long param = 1L;
        //when
        //then
        assertEquals(userRepository.findById(param).get().getNickname(),
                userService.getUserDtoById(param).getNickname());
        assertEquals(userRepository.findById(param).get().getFirstName(),
                userService.getUserDtoById(param).getFirstName());
        assertEquals(userRepository.findById(param).get().getLastName(),
                userService.getUserDtoById(param).getLastName());
    }

    @Test
    void should_get_user_with_id_1L() {
        //given
        long param = 1L;
        //when
        //then
        assertEquals(userRepository.findById(param).get().getNickname(),
                userService.getUserById(param).getNickname());
        assertEquals(userRepository.findById(param).get().getFirstName(),
                userService.getUserById(param).getFirstName());
        assertEquals(userRepository.findById(param).get().getLastName(),
                userService.getUserById(param).getLastName());
        assertEquals(userRepository.findById(param).get().getPassword(),
                userService.getUserById(param).getPassword());
    }

    @Test
    void should_verify_user_list() {
        //given
        Long param = 1L;
        //when
        //then
        for (UserDto user : userService.getUsersDto()) {
            assertEquals(userRepository.findById(param).get().getNickname(),
                    user.getNickname());
            param++;
        }
    }

    @Test
    void should_check_user_books_with_correct_id() {
        //given
        long param = 1L;
        //when
        bookService.borrowBook
                (Optional.of(bookService.getBookById(param)),
                        Optional.of(userService.getUserById(param)));
        //then
        assertTrue(userService.getUserBooksByUserId(param)
                .get().contains(bookService.getBookById(param)));
    }

    @Test
    void should_get_empty_optional_instead_of_book_set_for_user_with_incorrect_id() {
        //given
        long param = 10L;
        //when
        //then
        assertTrue(userService.getUserBooksByUserId(param)
                .isEmpty());
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

    @Test
    void updateUser() {
        //given
        long param = 1L;
        UserDto userDto = getExampleUserDtoData();
        userDto.setId(param);
        //when
        userService.updateUserData(userDto);
        //then
        assertEquals(userRepository.findById(param).get().getNickname(),
                userService.getUserDtoById(param).getNickname());
        assertEquals(userRepository.findById(param).get().getFirstName(),
                userService.getUserDtoById(param).getFirstName());
        assertEquals(userRepository.findById(param).get().getLastName(),
                userService.getUserDtoById(param).getLastName());
    }

    @Test
    void findCredentialsByNickname() {
        //given
        long param = 1L;
        //when

        //then
        assertEquals(
                userService.findCredentialsByNickname(
                        userService.getUserById(param).getNickname()).get().getNickname(),
                userRepository.findById(param).get().getNickname()
        );
        assertEquals(
                userService.findCredentialsByNickname(
                        userService.getUserById(param).getNickname()).get().getPassword(),
                userRepository.findById(param).get().getPassword()
        );
        Set<String> collect = userRepository.findById(param)
                .get()
                .getUserRoles()
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toSet());
        Set<String> roles = userService
                .findCredentialsByNickname(
                        userService
                                .getUserById(param)
                                .getNickname())
                .get()
                .getRoles();
        collect.removeAll(roles);
        assertTrue(collect.isEmpty());
    }

    private static UserDto getExampleUserDtoData() {
        UserDto userDto = new UserDto();
//        userDto.setId();
        userDto.setNickname("TabbyCat");
//        userDto.setPassword("VeraVerto");
        userDto.setFirstName("Minerva");
        userDto.setLastName("McGonagall");
        userDto.setUserRoles(Set.of("USER"));
        return userDto;
    }

    private static UserRegisterDto getExampleUserRegisterDtoData() {
        UserRegisterDto userRegisterDto =
                new UserRegisterDto(
                        "Padfoot",
                        "BlackDog",
                        "Sirius",
                        "Black",
                        "ihopethismailisnotused@gmail.com"
                );
//        userDto.setId();
        return userRegisterDto;
    }
}
