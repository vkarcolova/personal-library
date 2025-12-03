package com.verkarc.library.services.impl;

import com.verkarc.library.model.entity.GenreEntity;
import com.verkarc.library.repositories.GenreRepository;
import com.verkarc.library.services.GenreService;
import jakarta.persistence.GeneratedValue;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Optional<GenreEntity> findByGenre(String genre) {
        return genreRepository.findByName(genre);
    }

    @Override
    public Optional<GenreEntity> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public GenreEntity save(GenreEntity genreEntity) {
        return genreRepository.save(genreEntity);
    }

    @Override
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return genreRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return genreRepository.existsByName(name);
    }
}
