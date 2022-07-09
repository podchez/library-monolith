package com.podchez.librarymonolith.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Username should not be empty")
    @Size(min = 6, max = 30, message = "Username's length should be between 6 and 30 characters")
    @Column(name = "username")
    private String username;

    @NotNull(message = "Email should not be empty")
    @Size(min = 3, max = 100, message = "Email's length should be between 3 and 100 characters")
    @Email(message = "Email address should be in correct format: 'name@domain.com'")
    @Column(name = "email")
    private String email;

    @NotNull(message = "Password should not be empty")
    @Size(min = 8, max = 100, message = "Password's length should be between 8 and 100 characters")
    @Column(name = "password")
    private String password;

    @Column(name = "is_enabled")
    private Boolean isEnabled;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "accounts", fetch = FetchType.EAGER)
    private List<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(username, account.username) &&
                Objects.equals(email, account.email) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
