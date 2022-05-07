package com.podchez.librarymonolith.dto.mapper;

/**
 *
 * @param <D1> RequestDto
 * @param <E> Entity
 * @param <D2> ResponseDto
 */
public interface Mapper<D1, E, D2> {
    /**
     * Maps RequestDto to Entity
     */
    E toEntity(D1 reqDto);

    /**
     * Maps Entity to ResponseDto
     */
    D2 toRespDto(E entity);
}
