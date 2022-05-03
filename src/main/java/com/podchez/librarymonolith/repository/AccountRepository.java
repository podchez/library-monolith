package com.podchez.librarymonolith.repository;

import com.podchez.librarymonolith.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
