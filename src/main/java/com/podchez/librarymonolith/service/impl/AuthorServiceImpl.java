package com.podchez.librarymonolith.service.impl;

import com.podchez.librarymonolith.dto.AuthorDto;
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
    public List<AuthorDto> findAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorDto findById(Long id) {
        return authorMapper.toDto(authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id)));
    }

    @Override
    public AuthorDto save(AuthorDto authorDto) {
        String authorFullName = authorDto.getFullName();
        if (authorRepository.findByFullName(authorFullName).isPresent()) {
            throw new AuthorAlreadyExistsException(authorFullName);
        }

        authorDto.setId(null); // to avoid updating
        Author savedAuthor = authorRepository.save(authorMapper.toEntity(authorDto));
        return authorMapper.toDto(savedAuthor);
    }

    @Override
    public AuthorDto update(Long id, AuthorDto authorDto) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }

        String authorFullName = authorDto.getFullName();
        Optional<Author> authorWithSameFullName = authorRepository.findByFullName(authorFullName);
        if(authorWithSameFullName.isPresent() &&
                !authorWithSameFullName.get().getId().equals(id)) {
            throw new AuthorAlreadyExistsException(authorFullName);
        }

        authorDto.setId(id); // authorDto should have correct id
        Author updatedAuthor = authorRepository.save(authorMapper.toEntity(authorDto));
        return authorMapper.toDto(updatedAuthor);
    }

    @Override
    public void deleteById(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }
        authorRepository.deleteById(id);
    }
}
