package ru.aegorova.simbirsoftproject.repositories.custom;

import org.springframework.stereotype.Repository;
import ru.aegorova.simbirsoftproject.models.Book;
import ru.aegorova.simbirsoftproject.models.Genre;
import ru.aegorova.simbirsoftproject.utils.BookFilter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BookCustomRepositoryImpl implements BookCustomRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findByGenreAndPublicationDate(Genre genre
            , LocalDate publicationDate
            , BookFilter bookFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = criteriaQuery.from(Book.class);
        List<Predicate> predicates = new ArrayList<Predicate>();
        if (publicationDate != null && genre == null) {
            throw new IllegalArgumentException("Для книги задан год издания, но отсутствует жанр");
        }
        if (publicationDate != null) {
            if (bookFilter == null) {
                throw new IllegalArgumentException("Отсутствует вид фильрации для поиска по дате издания");
            }
            if (bookFilter.equals(BookFilter.EQUALS)) {
                predicates.add(criteriaBuilder.equal(book.get("publicationDate")
                        , publicationDate));
            }
            if (bookFilter.equals(BookFilter.GREATER)) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(book.get("publicationDate")
                        , publicationDate));
            }
            if (bookFilter.equals(BookFilter.LESS)) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(book.get("publicationDate")
                        , publicationDate));
            }
        }
        if (genre != null) {
            Root<Genre> genreRoot = criteriaQuery.from(Genre.class);
            predicates.add(criteriaBuilder.equal(genreRoot.get("id"), genre.getId()));
            criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
            Join<Genre, Book> genres = genreRoot.join("books");
            CriteriaQuery<Book> cq = criteriaQuery.select(genres);
            TypedQuery<Book> query = entityManager.createQuery(cq);
            return query.getResultList();
        }
        criteriaQuery.select(book)
                .where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<Book> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
