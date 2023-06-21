package com.library.springlibrary.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;
import jakarta.persistence.*;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "publication_id")
    private List<PublicationComment> commentaryList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publication that = (Publication) o;

        if (!title.equals(that.title)) return false;
        if (!publicationYear.equals(that.publicationYear)) return false;
        if (!publisher.equals(that.publisher)) return false;
        if (!authorFirstName.equals(that.authorFirstName)) return false;
        return authorLastName.equals(that.authorLastName);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + publicationYear.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + authorFirstName.hashCode();
        result = 31 * result + authorLastName.hashCode();
        return result;
    }
}
