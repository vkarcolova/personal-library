package com.verkarc.library.services.impl;

import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.model.entity.BookEntity;
import com.verkarc.library.services.AuthorService;
import com.verkarc.library.services.BookService;

import java.util.List;
import java.util.Optional;

public class BookServiceImpl implements BookService {
    @Override
    public BookEntity save(BookEntity book) {
        return null;
    }

    @Override
    public List<BookEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<BookEntity> findOne(Long id) {
        return Optional.empty();
    }
}
