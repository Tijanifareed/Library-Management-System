package com.internship.oasis.oasistaskone.dtos.responses;

import com.internship.oasis.oasistaskone.entities.Loans;

import java.util.List;

public class GetDebtorsResponse {
    private List<Loans> notReturnedLoan;

    public List<Loans> getNotReturnedLoan() {
        return notReturnedLoan;
    }

    public void setNotReturnedLoan(List<Loans> notReturnedLoan) {
        this.notReturnedLoan = notReturnedLoan;
    }
}
