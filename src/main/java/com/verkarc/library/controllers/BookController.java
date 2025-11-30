package com.verkarc.library.controllers;

import com.verkarc.library.mappers.Mapper;
import com.verkarc.library.model.dto.BookDTO;
import com.verkarc.library.model.entity.BookEntity;
import com.verkarc.library.services.BookService;
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

    private final BookService bookService;
    private Mapper<BookEntity, BookDTO> bookMapper;
    public BookController(BookService bookService, Mapper<BookEntity, BookDTO> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
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
