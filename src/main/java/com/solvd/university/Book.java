package com.solvd.university;

import java.util.Objects;

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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
