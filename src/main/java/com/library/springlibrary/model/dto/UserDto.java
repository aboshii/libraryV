package com.library.springlibrary.model.dto;

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
    @Size(min = 3)
    private String lastName;
    @NonNull
    @Size(min = 3)
    private String firstName;
    private Set<BookDto> borrowedBooks = new HashSet<>();

}
