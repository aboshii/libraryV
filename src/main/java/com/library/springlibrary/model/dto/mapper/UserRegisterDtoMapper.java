package com.library.springlibrary.model.dto.mapper;

import com.library.springlibrary.model.User;
import com.library.springlibrary.model.dto.UserDto;
import com.library.springlibrary.model.dto.UserRegisterDto;
import com.library.springlibrary.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserRegisterDtoMapper {
    BookDtoMapper bookDtoMapper;
    UserRepository userRepository;

    public User map(UserRegisterDto userDto){
        User user = new User();
        user.setNickname(userDto.getNickname());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        return user;
    }
}
