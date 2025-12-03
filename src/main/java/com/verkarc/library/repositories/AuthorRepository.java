package com.verkarc.library.repositories;

import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.model.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {

    @Query("SELECT a FROM AuthorEntity a WHERE a.name LIKE CONCAT(:letter, '%') ") //napr LIKE '%A'
    Iterable<AuthorEntity> findAuthorsStartsWithLetter(@Param("letter") String letter);

    Iterable<AuthorEntity> findByNameStartingWith(String prefix);

    boolean existsByName(String name);

    Optional<AuthorEntity> findByName(String name);

}
