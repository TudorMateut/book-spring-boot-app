package ro.sda.book_app.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.sda.book_app.model.Book;
import ro.sda.book_app.service.interfaces.BookService;

import java.util.List;

@RestController
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping(value = "/book")
    public ResponseEntity<Void> save(@RequestBody @Valid Book book) {
        bookService.save(book);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

//    @PostMapping(value = "/v2/book")
//    @ResponseStatus(HttpStatus.CREATED)
//    public void anotherSave (@RequestBody Book book) {
//        bookService.save(book);
//    }

    @GetMapping(value = "/books")
    public ResponseEntity<List<Book>> findAll() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping(value = "/find-book-by-id/{id}")
    public ResponseEntity<Book> findById(@PathVariable("id") long id) {
        Book result = bookService.findById(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }
}
