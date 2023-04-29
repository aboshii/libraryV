package com.library.springlibrary.model;

import lombok.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.MonthDay;
import java.time.Year;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor(force = true)
//@Entity
public class Magazine extends Publication {
    public static final String PUBLICATION_TYPE = "Magazine";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    MonthDay monthDay;

    public Magazine(@NonNull String title, @NonNull Year publicationYear, @NonNull String publisher,
                    @NonNull String authorFirstName, @NonNull String lastFirstName, int month, int day) {
        super(title, publicationYear, publisher, authorFirstName, lastFirstName);
        this.monthDay = MonthDay.of(month, day);
    }
    @Override
    public String toString() {
        return super.toString() +
                " monthDay='" + monthDay + '\'' +
                '}';
    }
}
