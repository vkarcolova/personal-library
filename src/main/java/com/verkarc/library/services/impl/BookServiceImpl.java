package com.verkarc.library.services.impl;

import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.model.entity.BookEntity;
import com.verkarc.library.model.entity.BookProgressEntity;
import com.verkarc.library.model.entity.GenreEntity;
import com.verkarc.library.repositories.AuthorRepository;
import com.verkarc.library.repositories.BookProgressRepository;
import com.verkarc.library.repositories.BookRepository;
import com.verkarc.library.repositories.GenreRepository;
import com.verkarc.library.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookProgressRepository bookProgressRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository, BookProgressRepository bookProgressRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.bookProgressRepository = bookProgressRepository;
    }

    @Override
    public BookEntity save(Long id, BookEntity book) {
        book.setId(id);
        return bookRepository.save(book);
    }

    @Override
    public BookEntity save(BookEntity book) {
        return bookRepository.save(book);
    }

    @Override
    public List<BookEntity> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Page<BookEntity> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Optional<BookEntity> findOne(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public BookEntity update(Long id, BookEntity bookEntity) {
        bookEntity.setId(id);
        return bookRepository.findById(id).map(existingBook ->
        {
            Optional.ofNullable(bookEntity.getTitle()).ifPresent(existingBook::setTitle);
            Optional.ofNullable(bookEntity.getStatus()).ifPresent(existingBook::setStatus);
            Optional.ofNullable(bookEntity.getCoverUrl()).ifPresent(existingBook::setCoverUrl);

            if (bookEntity.getAuthor() != null) {
                Long authorId = bookEntity.getAuthor().getId();
                AuthorEntity author = authorRepository.findById(authorId)
                        .orElseGet(() -> authorRepository.save(bookEntity.getAuthor()));
                existingBook.setAuthor(author);
            }

            if (bookEntity.getGenre() != null) {
                Long genreId = bookEntity.getGenre().getId();
                GenreEntity genre = genreRepository.findById(genreId)
                        .orElseGet(() -> genreRepository.save(bookEntity.getGenre()));
                existingBook.setGenre(genre);
            }

            if (bookEntity.getBookProgress() != null) {
                Long bookProgressId = bookEntity.getBookProgress().getId();
                BookProgressEntity progress = bookProgressRepository.findById(bookProgressId)
                        .orElseGet(() -> bookProgressRepository.save(bookEntity.getBookProgress()));

                existingBook.setBookProgress(progress);
            }
            return bookRepository.save(existingBook);
        }).orElse( null);
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return bookRepository.existsById(id);
    }

    @Override
    public boolean existsByTitleAndAuthor(String title, AuthorEntity author) {
        return bookRepository.existsByTitleAndAuthor(title,author);
    }

    @Override
    public boolean existsByTitle(String title) {
        return bookRepository.existsByTitle(title);
    }
}
