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
import java.util.Set;

@Entity
@Table(name = "person")
@EqualsAndHashCode(exclude = {"libraryCards"})
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person extends HumanAbstractEntity {

    private LocalDate birthDate;
    private Set<LibraryCard> libraryCards;
    private User user;


    @Column(name = "birth_date")
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "person")
    public Set<LibraryCard> getLibraryCards() {
        return libraryCards;
    }

    @JsonIgnore
    @JsonBackReference
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    public User getUser() {
        return user;
    }

}
