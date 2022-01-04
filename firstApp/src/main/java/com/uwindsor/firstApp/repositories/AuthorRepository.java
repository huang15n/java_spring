package com.uwindsor.firstApp.repositories;

import com.uwindsor.firstApp.models.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {

}
