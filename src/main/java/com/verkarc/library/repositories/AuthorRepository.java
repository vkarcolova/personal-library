package com.verkarc.library.repositories;

import com.verkarc.library.model.entity.AuthorEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends CrudRepository<AuthorEntity, Long> {

    @Query("SELECT a FROM Author a WHERE a.name LIKE CONCAT(:letter, '%') ") //napr LIKE '%A'
    Iterable<AuthorEntity> findAuthorsStartsWithLetter(@Param("letter") String letter);

    Iterable<AuthorEntity> findByNameStartingWith(String prefix);
}
