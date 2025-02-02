package com.internship.oasis_one.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddNewUserRequest {
    private String userName;
    private String emailAddress;
    private int libraryCardNumber;
    private String phoneNumber;
    private String password;
    private String homeAddress;
    private String profilePicture;



}
