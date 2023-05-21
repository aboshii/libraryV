package com.library.springlibrary.model.dto;

import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.User;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class UserDto {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private Set<BookDto> borrowedBooks = new HashSet<>();

}
