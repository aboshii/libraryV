package com.library.springlibrary.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class Publication2 {

    public Publication2(String title, String publisher) {
        this.title = title;
        this.publisher = publisher;
    }

    private String title;
    private String publisher;
}
