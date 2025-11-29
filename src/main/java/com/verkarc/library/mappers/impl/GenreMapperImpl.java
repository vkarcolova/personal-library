package com.verkarc.library.mappers.impl;

import com.verkarc.library.mappers.Mapper;
import com.verkarc.library.model.dto.BookDTO;
import com.verkarc.library.model.dto.GenreDTO;
import com.verkarc.library.model.entity.BookEntity;
import com.verkarc.library.model.entity.GenreEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GenreMapperImpl implements Mapper<GenreEntity, GenreDTO> {
    private ModelMapper modelMapper;

    public GenreMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public GenreDTO mapFromEntityToDto(GenreEntity entity) {
        return modelMapper.map(entity, GenreDTO.class);
    }

    @Override
    public GenreEntity mapFromDtoToEntity(GenreDTO dto) {
        return modelMapper.map(dto, GenreEntity.class);
    }
}
