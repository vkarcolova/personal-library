package com.verkarc.library.mappers.impl;

import com.verkarc.library.mappers.Mapper;
import com.verkarc.library.model.dto.BookDTO;
import com.verkarc.library.model.dto.BookProgressDTO;
import com.verkarc.library.model.entity.BookEntity;
import com.verkarc.library.model.entity.BookProgressEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookProgressImpl implements Mapper<BookProgressEntity, BookProgressDTO> {
    private final ModelMapper modelMapper;

    public BookProgressImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public BookProgressDTO mapFromEntityToDto(BookProgressEntity entity) {
        return modelMapper.map(entity, BookProgressDTO.class);
    }

    @Override
    public BookProgressEntity mapFromDtoToEntity(BookProgressDTO dto) {
        return modelMapper.map(dto, BookProgressEntity.class);
    }
}
