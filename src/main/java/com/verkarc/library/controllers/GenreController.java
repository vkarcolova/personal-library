package com.verkarc.library.controllers;

import com.verkarc.library.mappers.Mapper;
import com.verkarc.library.mappers.impl.GenreMapperImpl;
import com.verkarc.library.model.dto.GenreDTO;
import com.verkarc.library.model.entity.GenreEntity;
import com.verkarc.library.repositories.GenreRepository;
import com.verkarc.library.services.GenreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GenreController {
    private final GenreService genreService;
    private final Mapper<GenreEntity, GenreDTO> genreMapper;

    public GenreController(GenreService genreService, Mapper<GenreEntity, GenreDTO> genreMapper) {
        this.genreService = genreService;
        this.genreMapper = genreMapper;
    }

    @GetMapping(path = "/genres")
    public List<GenreDTO> getGenres(){
        return genreService.findAll().stream()
                .map(genreMapper::mapFromEntityToDto)
                .collect(Collectors.toList());
    }


    @PostMapping(path = "/genres")
    public ResponseEntity<GenreDTO> postGenre(@RequestBody GenreDTO genreDTO){
        if(genreDTO.getName() == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        if(genreService.existsByName(genreDTO.getName())) return new ResponseEntity<>(HttpStatus.CONFLICT);;
        GenreEntity genreEntity = genreMapper.mapFromDtoToEntity(genreDTO);
        GenreEntity savedEntity = genreService.save(genreEntity);
        return new ResponseEntity<>(genreMapper.mapFromEntityToDto(savedEntity),HttpStatus.CREATED);
    }


    @DeleteMapping(path = "/genres/{id}")
    public ResponseEntity deleteGenre(@PathVariable("id") Long id){
        if(genreService.exists(id)) {
            genreService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    //add
    //delete


}
