package com.library.springlibrary.model.dto.mapper;

import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.User;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.UserDto;
import com.library.springlibrary.repository.UserRepository;
import com.library.springlibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserDtoMapper {
    BookDtoMapper bookDtoMapper;
    UserRepository userRepository;

    public UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBorrowedBooks(
                user.getBorrowedBooks()
                        .stream()
                        .map(bookDtoMapper::map)
                        .collect(Collectors.toSet()));
        return userDto;
    }
    public User map(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        return user;
    }
}
