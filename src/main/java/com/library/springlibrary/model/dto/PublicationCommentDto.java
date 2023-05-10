package com.library.springlibrary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PublicationCommentDto {
    private String username;
    private String description;
}
