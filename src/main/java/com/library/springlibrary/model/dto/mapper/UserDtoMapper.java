package com.library.springlibrary.model.dto.mapper;

import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.User;
import com.library.springlibrary.model.dto.BookDto;
import com.library.springlibrary.model.dto.UserDto;
import com.library.springlibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserDtoMapper {
    BookDtoMapper bookDtoMapper;

    public UserDto map(User user) {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBorrowedBooks(
                user.getBorrowedBooks()
                        .stream()
                        .map(bookDtoMapper::map)
                        .collect(Collectors.toSet()));
        return userDto;
    }
}
