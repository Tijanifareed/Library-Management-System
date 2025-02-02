package com.internship.oasis.oasistaskone.dtos.requests;

import com.internship.oasis.oasistaskone.entities.BookCategory;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AddBookRequest {
    private String bookTitle;
    private String author;
    private int totalNumber;
    private LocalDate publicationDate;
    private BookCategory bookCategory;

    public BookCategory getBookCategory() {
        return bookCategory;
    }

    public void setBookCategory(BookCategory bookCategory) {
        this.bookCategory = bookCategory;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(int totalNumber) {
        this.totalNumber = totalNumber;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }
}
