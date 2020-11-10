package ru.aegorova.simbirsoftproject;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import ru.aegorova.simbirsoftproject.dto.Book;
import ru.aegorova.simbirsoftproject.dto.User;
import ru.aegorova.simbirsoftproject.dto.UserBook;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.ZonedDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class ValidationTest {

    public User emptyUser = new User();
    public Book emptyBook = new Book();
    public UserBook emptyUserBook = new UserBook();
    public UserBook userBookWithEmptyUserAndBook = new UserBook(emptyUser, emptyBook, ZonedDateTime.now());

    @Test
    public void userValidationTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(emptyUser);
        log.info("User: {}", emptyUser);
        assertEquals(4, constraintViolations.size());
        constraintViolations.forEach(cv ->
                log.error("Error! property: {}, value: {}, message: {}"
                        , cv.getPropertyPath()
                        , cv.getInvalidValue()
                        , cv.getMessage())
        );
    }

    @Test
    public void bookValidationTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(emptyBook);
        log.info("Book: {}", emptyBook);
        assertEquals(3, constraintViolations.size());
        constraintViolations.forEach(cv ->
                log.error("Error! property: {}, value: {}, message: {}"
                        , cv.getPropertyPath()
                        , cv.getInvalidValue()
                        , cv.getMessage())
        );
    }

    @Test
    public void userBookWithEmptyUserAndBookTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(userBookWithEmptyUserAndBook);
        log.info("Book: {}", userBookWithEmptyUserAndBook);
        assertEquals(7, constraintViolations.size());
        constraintViolations.forEach(cv ->
                log.error("Error! property: {}, value: {}, message: {}"
                        , cv.getPropertyPath()
                        , cv.getInvalidValue()
                        , cv.getMessage())
        );
    }

    @Test
    public void emptyUserBookValidationTest() {
        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(emptyUserBook);
        log.info("Book: {}", emptyUserBook);
        assertEquals(3, constraintViolations.size());
        constraintViolations.forEach(cv ->
                log.error("Error! property: {}, value: {}, message: {}"
                        , cv.getPropertyPath()
                        , cv.getInvalidValue()
                        , cv.getMessage())
        );
    }
}
