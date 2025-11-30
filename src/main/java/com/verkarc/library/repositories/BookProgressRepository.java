package com.verkarc.library.repositories;

import com.verkarc.library.model.entity.BookProgressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BookProgressRepository extends JpaRepository<BookProgressEntity, Long> {
    Optional<BookProgressEntity> findByBook_Id(Long bookId);
}
