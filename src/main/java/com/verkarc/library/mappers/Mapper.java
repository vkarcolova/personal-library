package com.verkarc.library.mappers;

public interface Mapper<E,D>{
    D mapFromEntityToDto(E e);
    E mapFromDtoToEntity(D d);
}
