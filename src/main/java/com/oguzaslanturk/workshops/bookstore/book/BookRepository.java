package com.oguzaslanturk.workshops.bookstore.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    Page<Book> findByNameContainsIgnoreCase(Pageable pageable, String name);
    Page<Book> findByIsbn(Pageable pageable, String isbn);
    Page<Book> findByNameContainsIgnoreCaseOrIsbn(Pageable pageable, String name, String isbn);

}
