package com.internship.oasis.oasistaskone.dtos.requests;

import com.internship.oasis.oasistaskone.entities.BookCategory;

public class BrowseBooksRequest {
    private BookCategory bookCategory;

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }
}
