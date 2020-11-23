package ru.aegorova.simbirsoftproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "library_card")
@EqualsAndHashCode(callSuper = false)
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibraryCard extends AbstractEntity {

    private Person person;
    private Book book;
    private ZonedDateTime turningDate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    public Person getPerson() {
        return person;
    }

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    public Book getBook() {
        return book;
    }

    @Column(name = "turning_date")
    public ZonedDateTime getTurningDate() {
        return turningDate;
    }

    @PostPersist
    public void toCreate() {
        setTurningDate(ZonedDateTime.now().plusDays(7));
    }

    public LibraryCard(Person person, Book book) {
        this.person = person;
        this.book = book;
    }
}
