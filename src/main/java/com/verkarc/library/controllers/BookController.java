package com.verkarc.library.controllers;

import com.verkarc.library.mappers.Mapper;
import com.verkarc.library.model.dto.BookDTO;
import com.verkarc.library.model.entity.BookEntity;
import com.verkarc.library.services.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
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

    @GetMapping(path = "/books")
    public Page<BookDTO> getAllBooks(Pageable pageable){
        Page<BookEntity> bookEntityList = bookService.findAll(pageable);

        Page<BookDTO> result = bookEntityList.map(bookMapper::mapFromEntityToDto);
        return result;
    }

/*
    @GetMapping(path = "/books")
    public List<BookDTO> getAllBooks(){
        List<BookEntity> bookEntityList = bookService.listAll();

        List<BookDTO> result = bookEntityList.stream()
                .map(bookMapper::mapFromEntityToDto)
                .collect(Collectors.toList());
        return result;
    }
*/
}
