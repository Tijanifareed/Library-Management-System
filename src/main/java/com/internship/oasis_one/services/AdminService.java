package com.internship.oasis_one.services;

import com.internship.oasis_one.dtos.requests.AddNewUserRequest;
import com.internship.oasis_one.dtos.responses.AddNewUserResponse;

public interface AdminService {
    AddNewUserResponse addNewUser(AddNewUserRequest request);
}
