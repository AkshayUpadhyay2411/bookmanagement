package com.learning.bookmanagement.services;

import com.learning.bookmanagement.domain.entities.BookEntity;

import java.util.List;

public interface BookService {

    BookEntity save(BookEntity book);

    List<BookEntity> findAll();

    BookEntity findBookById(Long id);

    BookEntity updateBookById(Long id, BookEntity updatedBook);

    boolean deleteBook(Long id);
}
