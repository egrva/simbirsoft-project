package ru.aegorova.simbirsoftproject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import ru.aegorova.simbirsoftproject.security.Role;

import javax.persistence.*;

@Entity
@Table(name = "library_user")
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends AbstractEntity{

    private String login;
    private String password;
    private Role role;
    private Person person;

    @JsonIgnore
    @JsonManagedReference
    @OneToOne
    @MapsId
    @JoinColumn(name = "person_id")
    public Person getPerson() {
        return person;
    }

    @Column(name = "login")
    public String getLogin() {
        return login;
    }

    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    @Enumerated(value = EnumType.STRING)
    public Role getRole() {
        return role;
    }
}
