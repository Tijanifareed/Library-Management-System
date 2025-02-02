package com.internship.oasis_one.services;

import com.internship.oasis_one.dtos.requests.AddNewUserRequest;
import com.internship.oasis_one.dtos.responses.AddNewUserResponse;
import com.internship.oasis_one.entities.User;
import com.internship.oasis_one.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private UserRepository userRepository;



    @Override
    public AddNewUserResponse addNewUser(AddNewUserRequest request) {
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmailAddress(request.getEmailAddress());
        user.setLibraryCardNumber(request.getLibraryCardNumber());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setHomeAddress(request.getHomeAddress());
        user.setProfilePicture(request.getProfilePicture());
        userRepository.save(user);
        AddNewUserResponse response = new AddNewUserResponse();
        response.setMessage("New User Created Successfully");
        return response;
    }
}
