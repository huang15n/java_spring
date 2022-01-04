package com.uwindsor.firstApp.repositories;

import com.uwindsor.firstApp.models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
