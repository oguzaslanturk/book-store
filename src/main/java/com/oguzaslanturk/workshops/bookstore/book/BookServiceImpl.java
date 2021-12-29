package com.oguzaslanturk.workshops.bookstore.book;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto getById(Long id) {
        final Optional<Book> book = bookRepository.findById(id);
        return book.isPresent() ? modelMapper.map(book.get(), BookDto.class) : null;
    }

    public BookPageDto getAll(Specification<Book> spec, Pageable pageable) {
        final Page<Book> pageOfBooks = bookRepository.findAll(spec, pageable);
        return new BookPageDto(pageable.getPageNumber(),
                pageOfBooks.getTotalPages(),
                pageOfBooks.getTotalElements(),
                pageOfBooks.getContent()
                        .stream()
                        .map(book -> modelMapper.map(book, BookDto.class))
                        .collect(Collectors.toList()));
    }
}
