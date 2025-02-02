package com.internship.oasis.oasistaskone.dtos.responses;


public class BorrowBookRequest {
    private int liabaryNumber;
    private Long bookId;

    public int getLiabaryNumber() {
        return liabaryNumber;
    }

    public void setUserId(int liabaryNumber) {
        this.liabaryNumber = liabaryNumber;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
