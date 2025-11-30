package com.verkarc.library.services;
import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.model.entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {

    BookEntity save(Long id, BookEntity book);
    BookEntity save( BookEntity book);


    List<BookEntity> listAll();

    Page<BookEntity> findAll(Pageable pageable);

    Optional<BookEntity> findOne(Long id);

    BookEntity update(Long id, BookEntity bookEntity);

    void delete(Long id);

    boolean exists(Long id);

    boolean existsByTitleAndAuthor(String title, AuthorEntity author);

    boolean existsByTitle(String title);
}
