package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.AuthorRequestDto;
import com.podchez.librarymonolith.dto.AuthorResponseDto;
import com.podchez.librarymonolith.dto.mapper.AuthorMapper;
import com.podchez.librarymonolith.entity.Author;
import com.podchez.librarymonolith.exception.AuthorAlreadyExistsException;
import com.podchez.librarymonolith.exception.AuthorNotFoundException;
import com.podchez.librarymonolith.repository.AuthorRepository;
import com.podchez.librarymonolith.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }


    @Override
    public List<AuthorResponseDto> findAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toRespDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDto findById(Long id) {
        return authorMapper.toRespDto(authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id)));
    }

    @Override
    public AuthorResponseDto findByFullName(String fullName) {
        return authorMapper.toRespDto(authorRepository.findByFullName(fullName)
                .orElseThrow(() -> new AuthorNotFoundException(fullName)));
    }

    @Override
    public AuthorResponseDto save(AuthorRequestDto authorReqDto) {
        String authorFullName = authorReqDto.getFullName();
        if (authorRepository.findByFullName(authorFullName).isPresent()) {
            throw new AuthorAlreadyExistsException(authorFullName);
        }

        Author savedAuthor = authorRepository.save(authorMapper.toEntity(authorReqDto));
        return authorMapper.toRespDto(savedAuthor);
    }

    @Override
    public AuthorResponseDto update(Long id, AuthorRequestDto authorReqDto) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }

        String authorFullName = authorReqDto.getFullName();
        Optional<Author> authorWithSameFullName = authorRepository.findByFullName(authorFullName);
        if (authorWithSameFullName.isPresent() &&
                !authorWithSameFullName.get().getId().equals(id)) {
            throw new AuthorAlreadyExistsException(authorFullName);
        }

        Author author = authorMapper.toEntity(authorReqDto);
        author.setId(id); // to avoid saving

        Author updatedAuthor = authorRepository.save(author);
        return authorMapper.toRespDto(updatedAuthor);
    }

    @Override
    public void deleteById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }
        authorRepository.deleteById(id);
    }
}
