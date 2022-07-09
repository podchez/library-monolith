package com.podchez.librarymonolith.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Title should not be empty")
    @Size(max = 255, message = "Title's length should be less than 255")
    @Column(name = "title")
    private String title;

    @NotNull(message = "Pages should not be empty")
    @Column(name = "pages")
    private Integer pages;

    @NotNull(message = "Price should not be empty")
    @Column(name = "price_in_cents")
    private Long priceInCents;

    @Column(name = "is_available")
    private Boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
                Objects.equals(pages, book.pages) &&
                Objects.equals(priceInCents, book.priceInCents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, pages, priceInCents);
    }
}
