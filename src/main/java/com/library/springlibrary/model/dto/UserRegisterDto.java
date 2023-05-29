package com.library.springlibrary.model.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

@AllArgsConstructor
@Getter
public class UserRegisterDto {
    @NonNull
    @Size(min = 6, max = 20)
    private String nickname;
    @NonNull
    @Size(min = 6, max = 26)
    private String password;
    @NonNull
    @Size(min = 3, max = 30)
    private String firstName;
    @NonNull
    @Size(min = 3, max = 30)
    private String lastName;
}
