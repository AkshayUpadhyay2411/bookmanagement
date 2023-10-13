package com.learning.bookmanagement.controllers;

import com.learning.bookmanagement.domain.entities.BookEntity;
import com.learning.bookmanagement.services.BookService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log
@RestController
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(path = "/")
    public String testServer() {
        return "Server working ...";
    }

    @PostMapping(path = "/books")
    public ResponseEntity<BookEntity> addBook(@RequestBody BookEntity book) {
        BookEntity savedBookEntity = bookService.save(book);
        return new ResponseEntity<>(savedBookEntity , HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public ResponseEntity < List<BookEntity> > allBooks() {
        log.info("All books method ... " );
        List <BookEntity> books = bookService.findAll();
        return new ResponseEntity<>(books , HttpStatus.OK);
    }

    @GetMapping(path = "/books/{id}")
    public ResponseEntity<BookEntity> getBookById(@PathVariable("id") Long id) {
        log.info("Find book by id method ... " + id);
        BookEntity book = bookService.findBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book , HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/books/{id}")
    public ResponseEntity<BookEntity> updateBook(@PathVariable("id") Long id , @RequestBody BookEntity updatedBook) {
        BookEntity updatedBookEntity = bookService.updateBookById(id , updatedBook);
        if (updatedBookEntity!=null) {
            return new ResponseEntity<>(updatedBookEntity , HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        boolean deleted = bookService.deleteBook(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
