package com.verkarc.library.services;
import com.verkarc.library.model.entity.GenreEntity;
import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<GenreEntity> findAll();

    Optional<GenreEntity> findByGenre(String genre);

    Optional<GenreEntity> findById(Long id);


    GenreEntity save(GenreEntity genreEntity);

    void delete(Long id);

    boolean exists(Long id);

    boolean existsByName(String name);

}
