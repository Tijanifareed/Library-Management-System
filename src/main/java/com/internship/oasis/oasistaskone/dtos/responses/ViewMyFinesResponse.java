package com.internship.oasis.oasistaskone.dtos.responses;

import com.internship.oasis.oasistaskone.entities.Fines;

import java.util.List;

public class ViewMyFinesResponse {
    private List<Fines> fines;

    public List<Fines> getFines() {
        return fines;
    }

    public void setFines(List<Fines> fines) {
        this.fines = fines;
    }
}
