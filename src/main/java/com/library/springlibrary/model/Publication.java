package com.library.springlibrary.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.Year;
@Data
@RequiredArgsConstructor
@MappedSuperclass
@NoArgsConstructor(force = true)
public abstract class Publication {
    @NonNull
    @Column(name = "title")
    private String title;
    @NonNull
    @Column(name = "publicationYear")
    private Year publicationYear;
    @NonNull
    @Column(name = "publisher")
    private String publisher;
    @NonNull
    @Column(name = "authorFirstName")
    private String authorFirstName;
    @NonNull
    @Column(name = "authorLastName")
    private String authorLastName;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User borrower;
    private LocalDateTime dateOfReturn;
}
