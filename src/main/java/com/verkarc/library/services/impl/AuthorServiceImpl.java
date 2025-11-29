package com.verkarc.library.services.impl;

import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.services.AuthorService;

import java.util.List;
import java.util.Optional;

public class AuthorServiceImpl implements AuthorService {
    @Override
    public AuthorEntity save(AuthorEntity author) {
        return null;
    }

    @Override
    public List<AuthorEntity> findAll() {
        return List.of();
    }

    @Override
    public Optional<AuthorEntity> findOne(Long id) {
        return Optional.empty();
    }


}
