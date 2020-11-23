package ru.aegorova.simbirsoftproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "author")
@EqualsAndHashCode(exclude = {"books"})
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author extends HumanAbstractEntity {

    @JsonIgnore
    private Set<Book> books;

    @JsonManagedReference
    @OneToMany(mappedBy = "author", orphanRemoval = true)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Set<Book> getBooks() {
        return books;
    }

    public Author(String firstName, String lastName, String middleName) {
        super(firstName, lastName, middleName);
    }
}
