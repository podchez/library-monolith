package com.podchez.librarymonolith.dto.mapper;

public interface Mapper<E, D> {

    D toDto(E entity);

    E toEntity(D dto);
}
