package com.verkarc.library.services;
import com.verkarc.library.model.entity.GenreEntity;
import java.util.List;

public interface GenreService {
    List<GenreEntity> findAll();

    GenreEntity save(GenreEntity genreEntity);

    void delete(Long id);

}
