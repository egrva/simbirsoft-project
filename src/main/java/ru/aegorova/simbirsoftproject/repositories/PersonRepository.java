package ru.aegorova.simbirsoftproject.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.aegorova.simbirsoftproject.models.Person;

import java.util.Set;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query("FROM Person person WHERE " +
            "person.firstName = :firstName and " +
            "person.lastName = :lastName and " +
            "person.middleName = :middleName")
    Set<Person> findPersonByFullName(@Param("firstName") String firstName
            , @Param("lastName") String lastName
            , @Param("middleName") String middleName);

}
