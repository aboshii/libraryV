package com.library.springlibrary.model;

import lombok.*;

import jakarta.persistence.*;
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
    @Column(name = "isbn")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (this.id.equals(book.id) && this.ISBN.equals(book.ISBN)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + id.hashCode();
        result = 31 * result + ISBN.hashCode();
        return result;
    }
}
