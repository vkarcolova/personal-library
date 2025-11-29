package com.verkarc.library.services.impl;

import com.verkarc.library.model.entity.GenreEntity;
import com.verkarc.library.services.GenreService;
import jakarta.persistence.GeneratedValue;

import java.util.List;

public class GenreServiceImpl implements GenreService {
    @Override
    public List<GenreEntity> findAll() {
        return List.of();
    }
}
