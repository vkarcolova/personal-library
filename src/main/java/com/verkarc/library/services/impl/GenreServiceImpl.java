package com.verkarc.library.services.impl;

import com.verkarc.library.model.entity.GenreEntity;
import com.verkarc.library.repositories.GenreRepository;
import com.verkarc.library.services.GenreService;
import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GenreServiceImpl implements GenreService {
    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }


    @Override
    public List<GenreEntity> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public GenreEntity save(GenreEntity genreEntity) {
        return genreRepository.save(genreEntity);
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }
}
