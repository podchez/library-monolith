package com.podchez.librarymonolith.security;

import com.podchez.librarymonolith.model.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class AccountDetails implements UserDetails {

    private final Account account;

    public AccountDetails(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return account.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return account.getIsEnabled();
    }

    @Override
    public boolean isAccountNonLocked() {
        return account.getIsEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return account.getIsEnabled();
    }

    @Override
    public boolean isEnabled() {
        return account.getIsEnabled();
    }

    public Account getAccount() {
        return account;
    }
}
