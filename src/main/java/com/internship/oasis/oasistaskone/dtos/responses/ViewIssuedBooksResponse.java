package com.internship.oasis.oasistaskone.dtos.responses;

import com.internship.oasis.oasistaskone.entities.Loans;

import java.util.List;

public class ViewIssuedBooksResponse {
    private List<Loans> loansList;

    public List<Loans> getLoansList() {
        return loansList;
    }

    public void setLoansList(List<Loans> loansList) {
        this.loansList = loansList;
    }
}
