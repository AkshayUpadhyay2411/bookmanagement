package com.learning.bookmanagement.repositories;

import com.learning.bookmanagement.domain.entities.BookEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<BookEntity , Long> {
}
