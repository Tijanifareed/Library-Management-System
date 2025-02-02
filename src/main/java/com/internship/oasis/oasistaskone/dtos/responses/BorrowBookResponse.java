package com.internship.oasis.oasistaskone.dtos.responses;

public class BorrowBookResponse {
    private String message;
    private Long loanId;

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
