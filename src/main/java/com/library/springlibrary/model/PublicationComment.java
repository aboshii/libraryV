package com.library.springlibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
@Table(name = "publication_commentaries")
public class PublicationComment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String username;
    @NonNull
    private String description;
    private LocalDateTime dateAdded;

    public PublicationComment(String username,
                              @NonNull String description) {
        this.username = username;
        this.description = description;
        this.dateAdded = LocalDateTime.now();
    }
}
