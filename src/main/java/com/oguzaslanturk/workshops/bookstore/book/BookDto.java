package com.oguzaslanturk.workshops.bookstore.book;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BookDto {

    @JsonProperty(required = true)
	@NotEmpty
	@NotBlank    
    private Long id;

    @JsonProperty(required = true)
	@NotEmpty
	@NotBlank
    private String name;

    @JsonProperty(required = true)
	@NotEmpty
	@NotBlank
    @Size(min = 9, max = 13)
    private String isbn;
}
