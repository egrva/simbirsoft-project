package ru.aegorova.simbirsoftproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Set;

@Setter
@EqualsAndHashCode(exclude = {"books"})
@Entity
@Table(name = "dim_genre")
@AllArgsConstructor
@NoArgsConstructor
public class Genre extends AbstractEntity {

    private String genreName;
    private Set<Book> books;

    @Column(name = "genre_name")
    public String getGenreName() {
        return genreName;
    }

    @JsonIgnore
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    },
            targetEntity = Book.class)
    @JoinTable(name = "book_genre_lnk",
            joinColumns = @JoinColumn(name = "genre_id",
                    nullable = false,
                    updatable = false),
            inverseJoinColumns = @JoinColumn(name = "book_id",
                    nullable = false,
                    updatable = false))
    public Set<Book> getBooks() {
        return books;
    }

    public Genre(String genreName) {
        this.genreName = genreName;
    }


}
