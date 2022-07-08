package com.podchez.librarymonolith.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Full name should not be empty")
    @Size(max = 255, message = "Full Name's length should be less than 255")
    @Column(name = "full_name")
    private String fullName;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books;
}
