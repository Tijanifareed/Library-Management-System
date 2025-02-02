package com.internship.oasis.oasistaskone.dtos.responses;

import com.internship.oasis.oasistaskone.entities.Book;

public class EditBookResponse {
    private Book book;

    public Book getBook(){
        return book;
    }

    public void setBook(Book book){
        this.book = book;
    }
}
