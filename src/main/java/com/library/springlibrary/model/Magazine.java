package com.library.springlibrary.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.MonthDay;
import java.time.Year;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor(force = true)
@Entity
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
