package ru.aegorova.simbirsoftproject.repositories.custom;

import org.springframework.stereotype.Repository;
import ru.aegorova.simbirsoftproject.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;


@Repository
public class AuthorCustomRepositoryImpl implements AuthorCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Author> findByFullNameAndCreationDate(LocalDate from
            , LocalDate to
            , String firstName
            , String lastName
            , String middleName) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> author = criteriaQuery.from(Author.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (firstName != null) {
            predicates.add(criteriaBuilder.equal(author.get("firstName"), firstName));
        }
        if (lastName != null) {
            predicates.add(criteriaBuilder.equal(author.get("lastName"), lastName));
        }
        if (middleName != null) {
            predicates.add(criteriaBuilder.equal(author.get("middleName"), middleName));
        }
        if (to != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(author.get("created")
                    , to.atStartOfDay(ZoneId.of("Europe/Moscow"))));
        }
        if (from != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(author.get("created")
                    , from.atStartOfDay(ZoneId.of("Europe/Moscow"))));
        }
        criteriaQuery.select(author)
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<Author> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

}
