package com.library.springlibrary.model;

import jakarta.persistence.*;

import java.util.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "borrower")
    private Set<Book> borrowedBooks = new HashSet<>();

}
