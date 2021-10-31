package com.solvd.university;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class Book {

    private static Book book;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
    private String fileName;

    @Getter(AccessLevel.PUBLIC)
    @Setter(AccessLevel.PUBLIC)
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
