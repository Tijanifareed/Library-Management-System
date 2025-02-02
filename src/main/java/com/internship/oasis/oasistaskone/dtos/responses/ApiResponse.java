package com.internship.oasis.oasistaskone.dtos.responses;

public class ApiResponse {
    private Object data;
    private boolean isSucessfull;

    public boolean isSucessfull() {
        return isSucessfull;
    }

    public void setSucessfull(boolean sucessfull) {
        isSucessfull = sucessfull;
    }

    public ApiResponse(boolean isSucessfull, Object data) {
        this.isSucessfull = isSucessfull;
        this.data = data;
    }
}
