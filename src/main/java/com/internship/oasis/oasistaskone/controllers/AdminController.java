package com.internship.oasis.oasistaskone.controllers;

import com.internship.oasis.oasistaskone.dtos.requests.*;
import com.internship.oasis.oasistaskone.dtos.responses.*;
import com.internship.oasis.oasistaskone.dtos.responses.DeleteBookResponse;
import com.internship.oasis.oasistaskone.services.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "*")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @PostMapping("create/new/user")
    public ResponseEntity<?>createUserAccount(@RequestBody AddNewUserRequest request){
try {
    AddNewUserResponse response = adminService.addNewUser(request);
    return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
}catch (RuntimeException | IOException exception){
    return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
}
    }

    @PostMapping("create/new/book")
    public ResponseEntity<?> createNewBook(@RequestBody AddBookRequest request){
        try{
            AddBookResponse response = adminService.addNewBookWith(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch(RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/existing/user")
    public ResponseEntity<?> deleteExistingUser(@RequestBody DeleteUserRequest  request){
        try{
            DeleteUserResponse response = adminService.deleteExistingUser(request);
            return new ResponseEntity<>(new ApiResponse(false, response), CREATED);
        }catch(RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @DeleteMapping("delete/existing/book")
    public ResponseEntity<?> deleteExistingBook(@RequestBody DeleteBookRequest request){
        try{
            DeleteBookResponse response = adminService.deleteExistingBook(request);
            return new ResponseEntity<>(new ApiResponse(false, response), CREATED);
        }catch(RuntimeException exception){
        return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("edit/existing/user")
    public ResponseEntity<?> editExistingUser(@RequestBody EditExistingUserRequest request){
        try{
            EditExistingUserResponse response = adminService.editExistingUser(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @PatchMapping("edit/existing/book")
    public ResponseEntity<?> editExistingBook(@RequestBody EditExistingBookRequest request){
        try{
            EditExistingBookResponse response = adminService.editExistingBook(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("all/loaned/book")
    public ResponseEntity<?> getAllDebtors(){
        try{
            GetDebtorsResponse response = adminService.getDebtors();
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @GetMapping("all/unpaid/fines")
    public ResponseEntity<?> showAllUnpaidFines(){
        try{
            GetDebtorsResponse response = adminService.getDebtors();
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("find/user/by/id")
    public ResponseEntity<?> findUserById(FindUserRequest request){
        try{
          FindUserResponse response =   adminService.findUserById(request);
          return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("verify/user/payment")
    public ResponseEntity<?> verifyPayment(Long fineId){
        try {
            VerifyUserPayment payment = adminService.verifyUserPayment(fineId);
            return new ResponseEntity<>(new ApiResponse(true, payment),CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }



}
