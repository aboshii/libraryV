package com.library.springlibrary.model.dto;

import com.library.springlibrary.model.Book;
import com.library.springlibrary.model.PublicationComment;
import lombok.*;

import java.time.Year;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class BookDto {
    private Long id;
    @NonNull
    private String title;
    @NonNull
    private Year publicationYear;
    @NonNull
    private String publisher;
    @NonNull
    private String authorFirstName;
    @NonNull
    private String authorLastName;
    private String borrowerData;
    @NonNull
    private String ISBN;
    private List<PublicationComment> commentaryList;

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
