package com.internship.oasis_one.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    private String emailAddress;
    private int libraryCardNumber;
    private String phoneNumber;
    private String password;
    private String homeAddress;
    private String profilePicture;
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

}
