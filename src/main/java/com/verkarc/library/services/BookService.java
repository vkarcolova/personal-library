package com.verkarc.library.services;

import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.model.entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity save(BookEntity book);

    List<BookEntity> findAll();

    Optional<BookEntity> findOne(Long id);
}
