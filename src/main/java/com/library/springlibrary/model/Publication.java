package com.library.springlibrary.model;

import lombok.*;

import jakarta.persistence.*;
import org.hibernate.engine.internal.Cascade;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

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
    @JoinColumn(name = "borrower")
    private User borrower;
    private LocalDateTime dateOfReturn;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "publication_id")
    private List<PublicationComment> commentaryList = new ArrayList<>();
}
