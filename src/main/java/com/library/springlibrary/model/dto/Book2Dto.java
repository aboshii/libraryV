package com.library.springlibrary.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Book2Dto {
    private String ISBN;
    private String title;
    private String publisher;
}
