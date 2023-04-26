package com.library.springlibrary.model;

import lombok.*;

import javax.persistence.*;
import java.time.Year;

@Getter
@Setter
@NoArgsConstructor(force = true)
@Entity
@Table(name = "books")
public class Book extends Publication {
    public static final String PUBLICATION_TYPE = "Book";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String ISBN;

    public Book(@NonNull String title, @NonNull Year publicationYear, @NonNull String publisher,
                String authorFirstName, String authorLastName,  @NonNull String ISBN) {
        super(title, publicationYear, publisher, authorFirstName, authorLastName);
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName()
                + " {"
                + "Title:" + this.getTitle()
                + ", Publication year:" + this.getPublicationYear()
                + ", Publisher:" + this.getPublisher()
                + ", Author name:" + this.getAuthorFirstName()
                + " " + this.getAuthorLastName()
                + ", ISBN:" + this.getISBN()
                + "}"
                ;
    }
}
