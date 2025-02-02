package com.internship.oasis.oasistaskone.dtos.responses;

import com.internship.oasis.oasistaskone.entities.Book;

import java.util.List;

public class BrowseBooksResponse {
    List<Book> books;

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
