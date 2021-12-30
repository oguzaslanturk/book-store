package com.oguzaslanturk.workshops.bookstore.book;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface BookService {
    BookDto getById(Long id);
    BookPageDto getAll(Specification<Book> spec, Pageable pageable);
    BookPageDto getAll(BookDto searchData, Pageable pageable);
    BookDto save(BookDto bookData);
    BookDto update(BookDto bookData);
    void deleteById(Long id);
}
