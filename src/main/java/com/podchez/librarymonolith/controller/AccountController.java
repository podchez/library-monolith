package com.podchez.librarymonolith.controller;

import com.podchez.librarymonolith.dto.AccountRequestDto;
import com.podchez.librarymonolith.dto.AccountResponseDto;
import com.podchez.librarymonolith.dto.JwtTokenDto;
import com.podchez.librarymonolith.dto.LoginDto;
import com.podchez.librarymonolith.dto.mapper.AccountMapper;
import com.podchez.librarymonolith.security.AccountDetails;
import com.podchez.librarymonolith.security.AccountDetailsService;
import com.podchez.librarymonolith.security.JwtUtil;
import com.podchez.librarymonolith.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final AccountDetailsService accountDetailsService;
    private final AccountMapper mapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AccountController(AccountService accountService, AccountDetailsService accountDetailsService,
                             AccountMapper mapper, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.accountDetailsService = accountDetailsService;
        this.mapper = mapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK) // 200
    public List<AccountResponseDto> findAll() {
        return accountService.findAll().stream()
                .map(mapper::toRespDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AccountResponseDto findById(@PathVariable Long id) {
        return mapper.toRespDto(accountService.findById(id));
    }

    @GetMapping("/username/{username}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AccountResponseDto findByUsername(@PathVariable String username) {
        return  mapper.toRespDto(accountService.findByUsername(username));
    }

    @GetMapping("/email/{email}")
    @ResponseStatus(HttpStatus.OK) // 200
    public AccountResponseDto findByEmail(@PathVariable String email) {
        return  mapper.toRespDto(accountService.findByEmail(email));
    }

    @PostMapping("/registration")
    @ResponseStatus(HttpStatus.OK) // 200
    public JwtTokenDto registration(@RequestBody AccountRequestDto accountReqDto) {
        accountService.save(mapper.toEntity(accountReqDto));

        String jwtToken = jwtUtil.GenerateToken(accountReqDto.getUsername());
//        String jwtToken = jwtUtil.GenerateToken(username,
//                accountDetailsService.loadUserByUsername(username).getAuthorities().stream()
//                        .map(GrantedAuthority::getAuthority)
//                        .collect(Collectors.toList())
//                        .toString());

        return new JwtTokenDto(jwtToken);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK) // 200
    public JwtTokenDto login(@RequestBody LoginDto loginDto) {
        UsernamePasswordAuthenticationToken authInputToken =
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());

        try {
            authenticationManager.authenticate(authInputToken);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect credentials");
        }

        String newJwtToken = jwtUtil.GenerateToken(loginDto.getUsername());
        return new JwtTokenDto(newJwtToken);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void update(@PathVariable Long id, @RequestBody AccountRequestDto accountReqDto) {
        accountService.update(id, mapper.toEntity(accountReqDto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // 204
    public void deleteById(@PathVariable Long id) {
        accountService.delete(id);
    }

    @GetMapping("/test")
    public String test() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetails accountDetails = (AccountDetails) authentication.getPrincipal();

        return accountDetails.getUsername();
    }
}
