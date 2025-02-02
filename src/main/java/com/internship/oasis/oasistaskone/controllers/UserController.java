package com.internship.oasis.oasistaskone.controllers;


import com.cloudinary.Api;
import com.internship.oasis.oasistaskone.dtos.requests.*;
import com.internship.oasis.oasistaskone.dtos.responses.*;
import com.internship.oasis.oasistaskone.repositories.UserRepository;
import com.internship.oasis.oasistaskone.services.user.LoginRequest;
import com.internship.oasis.oasistaskone.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@CrossOrigin(origins = "*")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("search/book")
    public ResponseEntity<?> searchForBook(SearchForBookRequest request){
        try{
            SearchForBookResponse response = userService.searchForABookWith(request);
            return new ResponseEntity<>( new ApiResponse(true, response), CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("search/byctegory")
    public ResponseEntity<?> searchByCategory(BrowseBooksRequest request){
        try{
            BrowseBooksResponse response = userService.browseBookByCategory(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("borrow/book")
    public ResponseEntity<?> borrowBook(BorrowBookRequest request){
        try{
            BorrowBookResponse response = userService.borrowBook(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch(RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("find/book")
    public ResponseEntity<?> findBookById(FindBookRequest request){
        try{
            FindBookResponse response = userService.findBookById(request);
            return new ResponseEntity<>(new ApiResponse(true, response), CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("return/book")
    public ResponseEntity<?> returnBook(ReturnBookRequest request){
        try{
            ReturnBookResponse response = userService.returnBook(request);
            return new ResponseEntity<>(new ApiResponse(true, response),CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()), BAD_REQUEST);
        }
    }

    @PostMapping("all/loans")
    public ResponseEntity<?> getAllMyLoan(ViewFinesRequest request){
        try{
            ViewMyFinesResponse response = userService.viewAllMyFines(request);
            return new ResponseEntity<>(new ApiResponse(true, response),CREATED);
        }catch (RuntimeException exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()),BAD_REQUEST);
        }
    }

    @PostMapping("view/borrowed/books")
    public ResponseEntity<?> getAllBorrowedBooks(ViewIssuedBooksRequest request){
        try {
            ViewIssuedBooksResponse response = userService.viewIssuedBooks(request);
            return new ResponseEntity<>(new ApiResponse(true, response),CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()),BAD_REQUEST);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(LoginRequest request){
        try {
            LoginResponse response = userService.login(request);
            return new ResponseEntity<>(new ApiResponse(true, response),CREATED);
        }catch (Exception exception){
            return new ResponseEntity<>(new ApiResponse(false, exception.getMessage()),BAD_REQUEST);
        }
    }

}
