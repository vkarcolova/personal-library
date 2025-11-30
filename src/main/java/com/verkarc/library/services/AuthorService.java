package com.verkarc.library.services;

import com.verkarc.library.model.entity.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    AuthorEntity save(AuthorEntity author);

    List<AuthorEntity> listAll();

    Optional<AuthorEntity> getOne(Long id);

    AuthorEntity update(Long id, AuthorEntity authorEntity);

    void delete(Long id);

    boolean exists(Long id);
    boolean existsByName(String name);

}
