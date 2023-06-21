package com.library.springlibrary.configuration;

import com.library.springlibrary.exceptions.UserNotFoundException;
import com.library.springlibrary.model.dto.UserCredentialsDto;
import com.library.springlibrary.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;
    static {
        User.builder()
                .username("admin")
                .password("hardtoguess")
                .roles("ADMIN")
                .build();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.findCredentialsByNickname(username)
                .map(this::createUsersDetails)
                .orElseThrow(() -> new UserNotFoundException(String.format("User with id: %s not found", username)));
    }

    private UserDetails createUsersDetails(UserCredentialsDto userCredentialsDto) {
        return User.builder()
                .username(userCredentialsDto.getNickname())
                .password(userCredentialsDto.getPassword())
                .roles(userCredentialsDto.getRoles().toArray(String[]::new))
                .build();
    }
}
