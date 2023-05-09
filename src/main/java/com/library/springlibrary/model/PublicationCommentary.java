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
    Long id;
    String username;
    @NonNull
    String description;
    LocalDateTime dateAdded;

    public PublicationCommentary(String username, @NonNull String description) {
        this.username = username;
        this.description = description;
        this.dateAdded = LocalDateTime.now();
    }
}
