package com.internship.oasis.oasistaskone.dtos.responses;

import com.internship.oasis.oasistaskone.entities.Loans;

import java.util.List;

public class ViewFinesResponse {
    List<Loans> loans;

    public List<Loans> getLoans() {
        return loans;
    }

    public void setLoans(List<Loans> loans) {
        this.loans = loans;
    }
}
