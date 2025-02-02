package com.internship.oasis.oasistaskone.services.user;

import com.internship.oasis.oasistaskone.dtos.requests.*;
import com.internship.oasis.oasistaskone.dtos.responses.ReturnBookResponse;
import com.internship.oasis.oasistaskone.dtos.responses.*;

public interface UserService {

    SearchForBookResponse searchForABookWith(SearchForBookRequest request);

    BrowseBooksResponse browseBookByCategory(BrowseBooksRequest request);

    BorrowBookResponse borrowBook(BorrowBookRequest request1);
    FindBookResponse findBookById(FindBookRequest request);

    ReturnBookResponse returnBook(ReturnBookRequest request3);

    ViewIssuedBooksResponse viewIssuedBooks(ViewIssuedBooksRequest request);

    ViewMyFinesResponse viewAllMyFines(ViewFinesRequest request);
    LoginResponse login(LoginRequest request);
}
