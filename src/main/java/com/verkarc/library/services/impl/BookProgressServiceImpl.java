package com.verkarc.library.services.impl;

import com.verkarc.library.model.entity.BookProgressEntity;
import com.verkarc.library.repositories.BookProgressRepository;
import com.verkarc.library.services.BookProgressService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BookProgressServiceImpl implements BookProgressService {

    private final BookProgressRepository bookProgressRepository;

    public BookProgressServiceImpl(BookProgressRepository bookProgressRepository) {
        this.bookProgressRepository = bookProgressRepository;
    }

    @Override
    public BookProgressEntity save(BookProgressEntity bookProgress) {
        return bookProgressRepository.save(bookProgress);
    }

    @Override
    public List<BookProgressEntity> findAll() {
        return bookProgressRepository.findAll();
    }

    @Override
    public Optional<BookProgressEntity> findOne(Long id) {
        return bookProgressRepository.findById(id);
    }

    @Override
    public Optional<BookProgressEntity> findOneFromBook(Long id) {
        return bookProgressRepository.findByBook_Id(id);
    }

    @Override
    public BookProgressEntity update(Long id, BookProgressEntity bookProgressEntity) {
       bookProgressEntity.setId(id);

       return bookProgressRepository.findById(id).map(existingBookProgressEntity -> {
           Optional.ofNullable(bookProgressEntity.getProgressPages()).ifPresent(existingBookProgressEntity::setProgressPages);
           Optional.ofNullable(bookProgressEntity.getNote()).ifPresent(existingBookProgressEntity::setNote);
           Optional.ofNullable(bookProgressEntity.getRating()).ifPresent(existingBookProgressEntity::setRating);
           Optional.ofNullable(bookProgressEntity.getTotalPages()).ifPresent(existingBookProgressEntity::setTotalPages);
           return bookProgressRepository.save(existingBookProgressEntity);}
       ).orElse(null);
    }

    @Override
    public void delete(Long id) {
        bookProgressRepository.deleteById(id);
    }
}
