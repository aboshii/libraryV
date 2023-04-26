package com.library.springlibrary.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.Year;
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public abstract class Publication {
    @NonNull
    @Column(name = "title")
    private String title;
    @NonNull
    private Year publicationYear;
    @NonNull
    private String publisher;
    @NonNull
    private String authorFirstName;
    @NonNull
    private String authorLastName;
    private String borrowerId;
    private LocalDateTime dateOfReturn;
}
