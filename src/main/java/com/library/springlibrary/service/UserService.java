package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.UserAlreadyExistException;
import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.model.UserRole;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.UserCredentialsDto;
import com.library.springlibrary.model.dto.UserDto;
import com.library.springlibrary.model.User;
import com.library.springlibrary.model.dto.UserRegisterDto;
import com.library.springlibrary.model.dto.mapper.BookDtoMapper;
import com.library.springlibrary.model.dto.mapper.UserCredentialsDtoMapper;
import com.library.springlibrary.model.dto.mapper.UserDtoMapper;
import com.library.springlibrary.repository.UserRepository;
import com.library.springlibrary.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";
    private final UserRepository userRepository;
    private final BookDtoMapper bookDtoMapper;
    private final UserDtoMapper userDtoMapper;
    private final Validator validator;
    private final PasswordEncoder passwordEncoder;
    private final UserRoleRepository userRoleRepository;

    @Transactional
    public UserDto addUser(UserDto userDto) {
        if (checkUserCreatorValidations(userDto)) {
            return null;
        }
        User user = userDtoMapper.map(userDto);
        userRepository.save(user);
        return userDtoMapper.map(user);
    }

    private boolean checkUserCreatorValidations(UserDto userDto) {
        Set<ConstraintViolation<UserDto>> errors = validator.validate(userDto);
        if (!errors.isEmpty()) {
            errors.forEach(err -> System.out.printf(
                    "User cannot be added, caused by: %s %s (%s)\n",
                    err.getPropertyPath(),
                    err.getMessage(),
                    err.getInvalidValue()
            ));
            return true;
        }
        return false;
    }
    private boolean checkUserCreatorValidations(UserRegisterDto userRegisterDto) {
        Set<ConstraintViolation<UserRegisterDto>> errors = validator.validate(userRegisterDto);
        if (!errors.isEmpty()) {
            errors.forEach(err -> System.out.printf(
                    "User cannot be added, caused by: %s %s (%s)\n",
                    err.getPropertyPath(),
                    err.getMessage(),
                    err.getInvalidValue()
            ));
            return true;
        }
        return false;
    }

    @Transactional
    public boolean registerUser(UserRegisterDto userRegisterDto){
        if (checkUserCreatorValidations(userRegisterDto)) {
            return false;
        }
        if (userRepository.findUserByNickname(userRegisterDto.getNickname()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        User user = new User();
        String passwordHashed = passwordEncoder.encode(userRegisterDto.getPassword());
        user.setNickname(userRegisterDto.getNickname());
        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setPassword(passwordHashed);
        Optional<UserRole> userRole = userRoleRepository.findByName(USER_ROLE);
        userRole.ifPresentOrElse(
                role -> user.getUserRoles().add(role),
                () -> {throw new NoSuchElementException();
                }
        );
        userRepository.save(user);
        return true;
    }

    public UserDto getUserDtoById(Long id) throws UserNotFoundException {
        return userDtoMapper.map(userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    public User getUserById(Long id) throws UserNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
    public ArrayList<UserDto> getUsers() {
        System.out.println("getuserslog");
        return (ArrayList) userRepository.findAll();
    }
    @Transactional
    public Optional<Set<BookDto>> getUserBooksByUserId(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User finalUser = optionalUser.get();
            Set<BookDto> borrowedBooks = finalUser.getBorrowedBooks()
                    .stream()
                    .map(bookDtoMapper::map)
                    .collect(Collectors.toSet());
            return Optional.of(borrowedBooks);
        } else {
            return Optional.empty();
        }
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void updateUser (UserDto userDto){
        User user = userDtoMapper.map(userDto);
        userRepository.save(user);
    }
    public Optional<UserCredentialsDto> findCredentialsByNickname(String nickname){
        return userRepository.findUserByNickname(nickname)
                .map(UserCredentialsDtoMapper::map);
    }
}