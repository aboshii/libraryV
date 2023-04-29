package com.library.springlibrary.model.dto;

import com.library.springlibrary.model.Publication;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.Year;

@Getter
@Setter
public class BookDto extends Publication {
        public static final String PUBLICATION_TYPE = "Book";
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NonNull
        private String ISBN;

        public BookDto(@NonNull String title, @NonNull Year publicationYear, @NonNull String publisher,
                    String authorFirstName, String lastFirstName, @NonNull String ISBN) {
            super(title, publicationYear, publisher, authorFirstName, lastFirstName);
            this.ISBN = ISBN;
        }
    }
