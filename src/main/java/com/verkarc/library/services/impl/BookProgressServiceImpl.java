package com.verkarc.library.services.impl;

import com.verkarc.library.model.entity.BookProgressEntity;
import com.verkarc.library.services.BookProgressService;

import java.util.List;
import java.util.Optional;

public class BookProgressServiceImpl implements BookProgressService {
    @Override
    public List<BookProgressEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<BookProgressEntity> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<BookProgressEntity> findOneFromBook(Long id) {
        return Optional.empty();
    }
}
