package com.library.springlibrary.service;

import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.UserDto;
import com.library.springlibrary.model.User;
import com.library.springlibrary.model.dto.mapper.BookDtoMapper;
import com.library.springlibrary.model.dto.mapper.UserDtoMapper;
import com.library.springlibrary.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private BookDtoMapper bookDtoMapper;
    private UserDtoMapper userDtoMapper;
    private Validator validator;

    @Transactional
    public UserDto addUser(UserDto userDto) {
        Set<ConstraintViolation<UserDto>> errors = validator.validate(userDto);
        if (!errors.isEmpty()) {
            errors.forEach(err -> System.out.printf(
                    "User cannot be added, caused by: %s %s (%s)\n",
                    err.getPropertyPath(),
                    err.getMessage(),
                    err.getInvalidValue()
            ));
            return null;
        }
        User user = userDtoMapper.map(userDto);
        userRepository.save(user);
        return userDtoMapper.map(user);
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
}