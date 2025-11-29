package com.verkarc.library.mappers.impl;

import com.verkarc.library.mappers.Mapper;
import com.verkarc.library.model.dto.AuthorDTO;
import com.verkarc.library.model.dto.BookDTO;
import com.verkarc.library.model.dto.BookProgressDTO;
import com.verkarc.library.model.entity.AuthorEntity;
import com.verkarc.library.model.entity.BookEntity;
import com.verkarc.library.model.entity.BookProgressEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDTO> {
    private final ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public BookDTO mapFromEntityToDto(BookEntity entity) {
        if(entity == null) return null;
        BookDTO result = modelMapper.map(entity, BookDTO.class);
        if(entity.getBookProgress() != null)
        {
            result.setBookProgress(modelMapper.map(entity.getBookProgress(), BookProgressDTO.class));
        }
        return result;
    }

    @Override
    public BookEntity mapFromDtoToEntity(BookDTO dto) {
        if(dto == null) return null;
        BookEntity result = modelMapper.map(dto, BookEntity.class);
        if(dto.getBookProgress() != null)
        {
            result.setBookProgress(modelMapper.map(dto.getBookProgress(), BookProgressEntity.class));
        }
        return result;
    }
}
