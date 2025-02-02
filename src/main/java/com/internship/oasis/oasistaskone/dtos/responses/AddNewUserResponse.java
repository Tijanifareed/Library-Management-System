package com.internship.oasis.oasistaskone.dtos.responses;


public class AddNewUserResponse {
    private String message;

    private int libraryNumber;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(int libraryNumber) {
        this.libraryNumber = libraryNumber;
    }
}
