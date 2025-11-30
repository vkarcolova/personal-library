package com.verkarc.library.services;

import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.model.entity.BookProgressEntity;

import java.util.List;
import java.util.Optional;

public interface BookProgressService {

    BookProgressEntity save(BookProgressEntity bookProgress);

    List<BookProgressEntity> findAll();

    Optional<BookProgressEntity> findOne(Long id);

    Optional<BookProgressEntity> findOneFromBook(Long id);

    BookProgressEntity update(Long id, BookProgressEntity bookProgressEntity);

    void delete(Long id);
}
