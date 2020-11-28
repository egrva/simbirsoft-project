package ru.aegorova.simbirsoftproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Setter
@EqualsAndHashCode(exclude = {"libraryCards", "genres"})
@Entity
@Table(name = "book")
@AllArgsConstructor
@NoArgsConstructor
public class Book extends AbstractEntity {

    private String name;
    private Author author;
    private Set<Genre> genres;
    private Set<LibraryCard> libraryCards;
    private LocalDate libraryDate;
    private LocalDate publicationDate;


    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    public Author getAuthor() {
        return author;
    }

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "book")
    public Set<LibraryCard> getLibraryCards() {
        return libraryCards;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Column(name = "library_date")
    public LocalDate getLibraryDate() {
        return libraryDate;
    }

    @Column(name = "publication_date")
    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    },
            targetEntity = Genre.class)
    @JoinTable(name = "book_genre_lnk",
            joinColumns = @JoinColumn(name = "book_id",
                    nullable = false,
                    updatable = false),
            inverseJoinColumns = @JoinColumn(name = "genre_id",
                    nullable = false,
                    updatable = false))
    public Set<Genre> getGenres() {
        return genres;
    }

    public Book(String name, Author author) {
        this.name = name;
        this.author = author;
    }

}
