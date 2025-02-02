package com.internship.oasis.oasistaskone.dtos.responses;

public class EditExistingBookResponse {
    private String message;
    private Long bookId;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
