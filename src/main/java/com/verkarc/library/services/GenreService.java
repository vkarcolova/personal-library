package com.verkarc.library.services;
import com.verkarc.library.model.entity.GenreEntity;
import java.util.List;

public interface GenreService {
    List<GenreEntity> findAll();

    GenreEntity findByGenre(String genre);


    GenreEntity save(GenreEntity genreEntity);

    void delete(Long id);

    boolean exists(Long id);

    boolean existsByName(String name);

}
