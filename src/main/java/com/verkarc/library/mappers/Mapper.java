package com.verkarc.library.mappers;

public interface Mapper<Entity,Dto>{
    Dto mapFromEntityToDto(Entity a);
    Entity mapFromDtoToEntity(Dto b);
}
