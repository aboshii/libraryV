package com.library.springlibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
@Table(name = "publication_commentaries")
public class PublicationCommentary {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @NonNull
    private String description;
    private LocalDateTime dateAdded;

    public PublicationCommentary(String username, @NonNull String description) {
        this.username = username;
        this.description = description;
        this.dateAdded = LocalDateTime.now();
    }
}
