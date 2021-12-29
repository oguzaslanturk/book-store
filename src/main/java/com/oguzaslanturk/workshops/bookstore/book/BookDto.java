package com.oguzaslanturk.workshops.bookstore.book;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class BookDto {

    @JsonProperty
    private Long id;

    @JsonProperty(required = true)
    private String name;

    @Size(min = 9, max = 13)
    private String isbn;
}
