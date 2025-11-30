package com.verkarc.library.controllers;

import com.verkarc.library.mappers.Mapper;
import com.verkarc.library.model.dto.BookDTO;
import com.verkarc.library.model.dto.BookProgressDTO;
import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.model.entity.BookEntity;
import com.verkarc.library.model.entity.BookProgressEntity;
import com.verkarc.library.model.entity.GenreEntity;
import com.verkarc.library.services.AuthorService;
import com.verkarc.library.services.BookProgressService;
import com.verkarc.library.services.BookService;
import com.verkarc.library.services.GenreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
public class BookController {

    private final AuthorService authorService;
    private final GenreService genreService;
    private final BookService bookService;
    private final BookProgressService bookProgressService;
    private final BookProgressEntity bookProgressEntity;
    private Mapper<BookEntity, BookDTO> bookMapper;
    private final Mapper<BookProgressEntity, BookProgressDTO> bookProgressMapper;

    public BookController(AuthorService authorService, GenreService genreService,
                          BookService bookService, BookProgressService bookProgressService, BookProgressEntity bookProgressEntity,
                          Mapper<BookEntity, BookDTO> bookMapper, Mapper<BookProgressEntity, BookProgressDTO> bookProgressMapper) {
        this.authorService = authorService;
        this.genreService = genreService;
        this.bookService = bookService;
        this.bookProgressService = bookProgressService;
        this.bookProgressEntity = bookProgressEntity;
        this.bookMapper = bookMapper;
        this.bookProgressMapper = bookProgressMapper;
    }

    @PostMapping(path = "/books")
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO){
        BookEntity bookEntity = bookMapper.mapFromDtoToEntity(bookDTO);
        boolean existing = (bookDTO.getId() != null && bookService.exists(bookDTO.getId()));
        AuthorEntity author = null;
        if (bookDTO.getAuthor() != null) {
            if (bookDTO.getAuthor().getId() != null) {
                author = authorService.getOne(bookDTO.getAuthor().getId())
                        .orElse(author = null);
                if(!existing && author != null) existing = bookService.existsByTitleAndAuthor(bookDTO.getTitle(),author);
            } else {
                author = new AuthorEntity();
                author.setName(bookDTO.getAuthor().getName());
                if(!authorService.existsByName(author.getName())) author = authorService.save(author);
            }
        }

        GenreEntity genre = null;
        if (bookDTO.getGenre()  != null && bookDTO.getGenre().getName() != null) {
            GenreEntity genreEntity = genreService.findByGenre(bookDTO.getGenre().getName());
            if(genreEntity != null) genre = genreEntity;
            else {
                genre.setName(bookDTO.getGenre().getName());
                genre = genreService.save(genre);
            }
        }

        BookProgressEntity progress = null;
        if (bookDTO.getBookProgress() != null){
            progress = bookProgressMapper.mapFromDtoToEntity(bookDTO.getBookProgress());
            progress = bookProgressService.save(progress);
        }
        bookEntity.setAuthor(author);
        bookEntity.setGenre(genre);
        bookEntity.setBookProgress(bookProgressEntity);

        if(!existing){
            BookEntity savedBook = bookService.save(bookEntity);
            return new ResponseEntity<>(bookMapper.mapFromEntityToDto(savedBook), HttpStatus.CREATED);
        } else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @PutMapping(path = "/books")
    public ResponseEntity<BookDTO> createUpdateBook(@RequestBody BookDTO bookDTO){
        BookEntity bookEntity = bookMapper.mapFromDtoToEntity(bookDTO);
        boolean existing = bookService.exists(bookEntity.getId());
        BookEntity savedBook = bookService.save(bookEntity.getId(), bookEntity);
        if(existing){
            return new ResponseEntity<>(bookMapper.mapFromEntityToDto(savedBook), HttpStatus.OK);
        } else return new ResponseEntity<>(bookMapper.mapFromEntityToDto(savedBook), HttpStatus.CREATED);
    }

    @GetMapping(path = "/books")
    public Page<BookDTO> getAllBooks(Pageable pageable){
        Page<BookEntity> bookEntityList = bookService.findAll(pageable);
        Page<BookDTO> result = bookEntityList.map(bookMapper::mapFromEntityToDto);
        return result;
    }
    @GetMapping(path = "/books/{id}")
    public ResponseEntity<BookDTO> getBookFromId(@PathVariable Long id){
        return bookService.findOne(id).map(book -> {
            BookDTO foundDto = bookMapper.mapFromEntityToDto(book);
            return new ResponseEntity<>(foundDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping(path = "/books/{id}")
    public ResponseEntity<BookDTO> partialUpdateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO){
        if(!bookService.exists(id)) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        bookDTO.setId(id);
        BookEntity bookEntity = bookMapper.mapFromDtoToEntity(bookDTO);
        BookEntity savedEntity = bookService.update(id, bookEntity);
        return new ResponseEntity<>(bookMapper.mapFromEntityToDto(savedEntity), HttpStatus.OK);
    }

    @DeleteMapping(path = "/books/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id){
        if(bookService.exists(id)) {
            bookService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


}
