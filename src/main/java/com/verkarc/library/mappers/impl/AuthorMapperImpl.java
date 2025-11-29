package com.verkarc.library.mappers.impl;

import com.verkarc.library.mappers.Mapper;
import com.verkarc.library.model.dto.AuthorDTO;
import com.verkarc.library.model.entity.AuthorEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity,AuthorDTO> {
    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public AuthorDTO mapFromEntityToDto(AuthorEntity entity) {
        return modelMapper.map(entity, AuthorDTO.class);
    }

    @Override
    public AuthorEntity mapFromDtoToEntity(AuthorDTO dto) {
        return modelMapper.map(dto, AuthorEntity.class);
    }
}
