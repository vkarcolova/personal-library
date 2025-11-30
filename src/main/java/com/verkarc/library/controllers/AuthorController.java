package com.verkarc.library.controllers;

import com.verkarc.library.mappers.Mapper;
import com.verkarc.library.model.dto.AuthorDTO;
import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.services.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    //list all, delete

    private final AuthorService authorService;
    private Mapper<AuthorEntity, AuthorDTO> authorMapper;

    public AuthorController(AuthorService authorService, Mapper<AuthorEntity, AuthorDTO> authorMapper){
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @GetMapping(path = "/authors/{id}")
    public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable("id") Long id){
        Optional<AuthorEntity> author = authorService.getOne(id);

        return author.map(authorEntity -> {
            return new ResponseEntity<>(authorMapper.mapFromEntityToDto(authorEntity),HttpStatus.OK);
        }).orElse(  new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/authors")
    public List<AuthorDTO> getAllAuthors(){
        List<AuthorEntity> authorEntityList = authorService.listAll();
        List<AuthorDTO> result = new ArrayList<>();
        for(AuthorEntity entity : authorEntityList){
            result.add(authorMapper.mapFromEntityToDto(entity));
        }
//        List<AuthorDTO> result2 = authorEntityList.stream()
//                .map(authorMapper::mapFromEntityToDto)
//                .collect(Collectors.toList());
//
//        List<AuthorDTO> result3 = authorEntityList.stream()
//                .map(entity -> authorMapper.mapFromEntityToDto(entity))
//                .collect(Collectors.toList());

        return result;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDTO> createAuthor(@RequestBody AuthorDTO authorDTO){
        AuthorEntity authorEntity = authorMapper.mapFromDtoToEntity(authorDTO);
        AuthorEntity savedEntity = authorService.save(authorEntity);
        return new ResponseEntity<>(authorMapper.mapFromEntityToDto(savedEntity),HttpStatus.CREATED);
    }

}
