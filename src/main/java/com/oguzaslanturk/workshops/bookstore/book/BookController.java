package com.oguzaslanturk.workshops.bookstore.book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the book",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = BookDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Book not found", content = @Content)
    })
    public ResponseEntity<BookDto> getById(@PathVariable Long id) {
        BookDto book = bookService.getById(id);
        return book != null ? ResponseEntity.ok(book) : ResponseEntity.notFound().build();
    }



    /*
        Use Spec API to handle dynamic filtering.
    @GetMapping(path = "/")
    @Operation(summary = "Returns a list of books sorted/filtered based on the query parameters.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of books",
                    content = {
                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = BookPageDto.class))
                    }
            )
    })
    public ResponseEntity<BookPageDto> getAll(
            @And({
                    @Spec(path = "name", spec = Like.class),
                    @Spec(path = "isbn", spec = Equal.class)
            }) Specification<Book> bookSpec,
            Pageable pageable
    ) {
        return ResponseEntity.ok(bookService.getAll(bookSpec, pageable));
    }
     */

    @GetMapping("/")
    public ResponseEntity<BookPageDto> getAll(BookDto bookDto, Pageable pageable) {
        return ResponseEntity.ok(bookService.getAll(bookDto, pageable));
    }

    @PostMapping("/")
    public ResponseEntity<BookDto> save(@RequestBody @Valid BookDto bookDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDto));
    }

    @PutMapping("/")
    public ResponseEntity<BookDto> update(@RequestBody @Valid BookDto bookDto) {
        if (bookService.getById(bookDto.getId()) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bookService.update(bookDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if (bookService.getById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
