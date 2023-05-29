package com.library.springlibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Set;

@AllArgsConstructor
@Getter
public class UserCredentialsDto {
    private String nickname;
    private String password;
    private Set<String> roles;
}
