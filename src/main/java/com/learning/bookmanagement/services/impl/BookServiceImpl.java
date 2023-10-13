package com.learning.bookmanagement.services.impl;

import com.learning.bookmanagement.domain.entities.BookEntity;
import com.learning.bookmanagement.repositories.BookRepository;
import com.learning.bookmanagement.services.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookEntity save(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> findAll() {
        return (List<BookEntity>) bookRepository.findAll();
    }

    @Override
    public BookEntity findBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public BookEntity updateBookById(Long id, BookEntity updatedBook) {
        BookEntity existingBook = bookRepository.findById(id).orElse(null);

        if (existingBook != null) {
            // Update properties if they are present in the request
            if (updatedBook.getTitle() != null) {
                existingBook.setTitle(updatedBook.getTitle());
            }
            if (updatedBook.getAuthor() != null) {
                existingBook.setAuthor(updatedBook.getAuthor());
            }
            if (updatedBook.getIsbn() != null) {
                existingBook.setIsbn(updatedBook.getIsbn());
            }
            if (updatedBook.getPublishedDate() != null) {
                existingBook.setPublishedDate(updatedBook.getPublishedDate());
            }
            return bookRepository.save(existingBook);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean deleteBook(Long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;
        }
        else {
            return false;
        }
    }
}
