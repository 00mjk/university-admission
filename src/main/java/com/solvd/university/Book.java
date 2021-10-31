package com.solvd.university;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter(AccessLevel.PUBLIC)
@Setter(AccessLevel.PUBLIC)
public class Book {

    private static Book book;

    private String fileName;
    private Genre genre;

    private Book() {
    }

    public static Book getInstance() {
        if (Objects.isNull(book)) {
            book = new Book();
        }
        return book;
    }
}
