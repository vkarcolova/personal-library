package com.verkarc.library.repositories;

import com.verkarc.library.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends JpaRepository<GenreEntity, Long> {
}
