package com.internship.oasis.oasistaskone.services.user;

public class LoginRequest {
    private String email;
    private int libaryCardNumber;

    public int getLibaryCardNumber() {
        return libaryCardNumber;
    }

    public void setLibaryCardNumber(int libaryCardNumber) {
        this.libaryCardNumber = libaryCardNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
