package com.verkarc.library.services.impl;

import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.repositories.AuthorRepository;
import com.verkarc.library.services.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public AuthorEntity save(AuthorEntity author) {
        return authorRepository.save(author);
    }

    @Override
    public List<AuthorEntity> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<AuthorEntity> getOne(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public AuthorEntity update(Long id, AuthorEntity authorEntity) {
        authorEntity.setId(id);
        return authorRepository.findById(id).map(existingAuthor -> {
            Optional.ofNullable(authorEntity.getName()).ifPresent(existingAuthor::setName);


            return authorRepository.save(existingAuthor);
        }).orElse(null);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return authorRepository.existsById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return authorRepository.existsByName(name);
    }

    @Override
    public Optional<AuthorEntity> getByName(String name) {
        return authorRepository.findByName(name);
    }
}
