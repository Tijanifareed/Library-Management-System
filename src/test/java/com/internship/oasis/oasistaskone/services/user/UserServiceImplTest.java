package com.internship.oasis.oasistaskone.services.user;

import com.internship.oasis.oasistaskone.dtos.responses.ReturnBookResponse;
import com.internship.oasis.oasistaskone.dtos.requests.*;
import com.internship.oasis.oasistaskone.dtos.responses.*;
import com.internship.oasis.oasistaskone.entities.BookCategory;
import com.internship.oasis.oasistaskone.repositories.BookRepository;
import com.internship.oasis.oasistaskone.repositories.UserRepository;
import com.internship.oasis.oasistaskone.services.admin.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        bookRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Test
    public void testThatUserCanSearchForBook(){
        AddBookRequest request = new AddBookRequest();
        request.setBookTitle("Beauty and the beast");
        request.setAuthor("buhari");
        request.setPublicationDate(LocalDate.now());
        request.setTotalNumber(50);
        request.setBookCategory(BookCategory.FANTASY);
        AddBookResponse response = adminService.addNewBookWith(request);
        SearchForBookRequest request1 = new SearchForBookRequest();
        request1.setBookDetail("BeautY and the beAst");
        SearchForBookResponse response1 = userService.searchForABookWith(request1);
        System.out.println(response1.getBook().toString());
        assertThat(response).isNotNull();
    }

    @Test
    public void testThatUserCanBrowseBooksByCategory(){
        AddBookRequest request = new AddBookRequest();
        request.setBookTitle("Beauty and the beast");
        request.setAuthor("buhari");
        request.setPublicationDate(LocalDate.now());
        request.setTotalNumber(50);
        request.setBookCategory(BookCategory.FANTASY);
        AddBookResponse response = adminService.addNewBookWith(request);
        BrowseBooksRequest request1 = new BrowseBooksRequest();
        request1.setBookCategory(BookCategory.FANTASY);
        BrowseBooksResponse response1 = userService.browseBookByCategory(request1);
        assertThat(response1.getBooks().size()).isEqualTo(1);
    }

    @Test
    public void testThatUserCanBorrowBook() throws IOException {
        AddNewUserRequest request2 = new AddNewUserRequest();
        request2.setUserName("Fareed Tijani");
        request2.setEmailAddress("fareedtijani2810@gmail.com");
        request2.setPhoneNumber("09117474727");
        request2.setPassword("freddie");
        request2.setHomeAddress("sabo, yaba");
        AddNewUserResponse response2 =  adminService.addNewUser(request2);
        AddBookRequest request = new AddBookRequest();
        request.setBookTitle("Beauty and the beast");
        request.setAuthor("buhari");
        request.setPublicationDate(LocalDate.now());
        request.setTotalNumber(50);
        request.setBookCategory(BookCategory.FANTASY);
        AddBookResponse response = adminService.addNewBookWith(request);
        BorrowBookRequest request1 = new BorrowBookRequest();
        request1.setBookId(response.getBookId());
        request1.setUserId(response2.getLibraryNumber());
        BorrowBookResponse response1 = userService.borrowBook(request1);
        assertThat(response1).isNotNull();
        assertThat(response1.getMessage()).isEqualTo("Thanks for borrowing this book Proceed to the librarian to collect The book");
    }

    @Test
    public void testThatUserCanReturnBook() throws IOException {
        AddNewUserRequest request2 = new AddNewUserRequest();
        request2.setUserName("Fareed Tijani");
        request2.setEmailAddress("fareedtijani2810@gmail.com");
        request2.setPhoneNumber("09117474727");
        request2.setPassword("freddie");
        request2.setHomeAddress("sabo, yaba");
        AddNewUserResponse response2 =  adminService.addNewUser(request2);
        AddBookRequest request = new AddBookRequest();
        request.setBookTitle("Beauty and the beast");
        request.setAuthor("buhari");
        request.setPublicationDate(LocalDate.now());
        request.setTotalNumber(50);
        request.setBookCategory(BookCategory.FANTASY);
        AddBookResponse response = adminService.addNewBookWith(request);
        BorrowBookRequest request1 = new BorrowBookRequest();
        request1.setBookId(response.getBookId());
        request1.setUserId(response2.getLibraryNumber());
        BorrowBookResponse response1 = userService.borrowBook(request1);
        ReturnBookRequest request3 = new ReturnBookRequest();
        request3.setLoanId(response1.getLoanId());
        ReturnBookResponse returnBookResponse = userService.returnBook(request3);
        assertThat(returnBookResponse.getMessage()).isEqualTo("Book returned successfully");
    }


}