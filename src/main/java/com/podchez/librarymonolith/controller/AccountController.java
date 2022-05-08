package com.podchez.librarymonolith.controller;

import com.podchez.librarymonolith.dto.AccountRequestDto;
import com.podchez.librarymonolith.dto.AccountResponseDto;
import com.podchez.librarymonolith.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<AccountResponseDto> findAll() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AccountResponseDto findById(@PathVariable Long id) {
        return accountService.findById(id);
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AccountResponseDto findByEmail(@PathVariable String email) {
        return accountService.findByEmail(email);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // 201
    public AccountResponseDto save(@RequestBody AccountRequestDto accReqDto) {
        return accountService.save(accReqDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AccountResponseDto update(@PathVariable Long id, @RequestBody AccountRequestDto accReqDto) {
        return accountService.update(id, accReqDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteById(@PathVariable Long id) {
        accountService.deleteById(id);
    }
}
