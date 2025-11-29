package com.verkarc.library.services;

import com.verkarc.library.model.entity.BookProgressEntity;

import java.util.List;
import java.util.Optional;

public interface BookProgressService {

    List<BookProgressEntity> findAll();

    Optional<BookProgressEntity> findOne(Long id);

    Optional<BookProgressEntity> findOneFromBook(Long id);

}
