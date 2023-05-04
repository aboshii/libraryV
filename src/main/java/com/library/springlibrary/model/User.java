package com.library.springlibrary.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "borrower", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Book> borrowedBooks = new HashSet<>();

}
