package com.library.springlibrary.model.dto.mapper;

import com.library.springlibrary.model.User;
import com.library.springlibrary.model.UserRole;
import com.library.springlibrary.model.dto.UserCredentialsDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserCredentialsDtoMapper {

    public static UserCredentialsDto map(User user) {
        String nickname = user.getNickname();
        String password = user.getPassword();
        Set<String> roles = user.getUserRoles()
                .stream()
                .map(UserRole::getName)
                .collect(Collectors.toSet());
        UserCredentialsDto userCredentialsDto =
                new UserCredentialsDto(nickname, password, roles);
        return userCredentialsDto;
    }
}
