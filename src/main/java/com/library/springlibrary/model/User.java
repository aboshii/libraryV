package com.library.springlibrary.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nickname;
    @NonNull
    private String password;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "borrower", cascade = {CascadeType.PERSIST})
    //CascadeType.REMOVE
    private Set<Book> borrowedBooks = new HashSet<>();
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns =  @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<UserRole> userRoles = new HashSet<>();

    public String getUserData() {
        return getFirstName() + " " + getLastName();
    }
}
