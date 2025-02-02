package com.internship.oasis.oasistaskone.dtos.responses;

import com.internship.oasis.oasistaskone.entities.Book;

import java.util.List;

public class SearchForBookResponse {
    List<Book> book;

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }
}
