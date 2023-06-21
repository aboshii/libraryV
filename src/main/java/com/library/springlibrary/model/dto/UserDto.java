package com.library.springlibrary.model.dto;

import com.library.springlibrary.model.UserRole;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class UserDto {
    private Long id;
    @NonNull
    @Size(min = 6, max = 20)
    private String nickname;
    @NonNull
//    @Size(min = 6, max = 26)
//    private String password;
    @Size(min = 3, max = 30)
    private String firstName;
    @Size(min = 3, max = 30)
    private String lastName;
    private String emailAddress;
    private Set<String> userRoles;
    private Set<BookDto> borrowedBooks = new HashSet<>();
}
