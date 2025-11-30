package com.verkarc.library.repositories;

import com.verkarc.library.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
    boolean existsByName(String name);

    GenreEntity findByGenre(String genre);
}
