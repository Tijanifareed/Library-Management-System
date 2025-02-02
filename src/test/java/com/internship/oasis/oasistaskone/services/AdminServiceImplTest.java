package com.internship.oasis.oasistaskone.services;

import com.internship.oasis.oasistaskone.dtos.requests.*;
import com.internship.oasis.oasistaskone.dtos.responses.*;
import com.internship.oasis.oasistaskone.entities.BookCategory;
import com.internship.oasis.oasistaskone.entities.User;
import com.internship.oasis.oasistaskone.exceptions.EmailAlreadyExists;
import com.internship.oasis.oasistaskone.exceptions.PhoneNumberAlreadyExists;
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
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest

public class AdminServiceImplTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
        bookRepository.deleteAll();
    }
    @Autowired
    private AdminService adminService;


    @Test
    public void testThatAdminCanAddANewUser() throws IOException {
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Fareed Tijani");
        request.setEmailAddress("fareedtijani2810@gmail.com");
        request.setPhoneNumber("09117474727");
        request.setPassword("freddie");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response =  adminService.addNewUser(request);
        assertThat(response.getMessage()).isEqualTo("New User Created Successfully");
    }

    @Test
    public void testThatUserLibraryNumberIsUnique() throws IOException {
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Austine Joy");
        request.setEmailAddress("austinejoyz@gmail.coms");
        request.setPhoneNumber("09117474727");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response = adminService.addNewUser(request);
        AddNewUserRequest request1 = new AddNewUserRequest();
        request1.setUserName("Austine Joy");
        request1.setEmailAddress("austinejoyz@email.com");
        request1.setPhoneNumber("09117474728");
        request1.setHomeAddress("sabo, yaba");
        AddNewUserResponse response1 = adminService.addNewUser(request1);
        assertThat(response1.getLibraryNumber()).isNotEqualTo(response.getLibraryNumber());
    }


    @Test
    public void testThatUsersDoNotHaveDuplicateEmails() throws IOException {
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Austine Joy");
        request.setEmailAddress("austinejoyz@gmail.com");
        request.setPhoneNumber("0909998876");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response = adminService.addNewUser(request);
        AddNewUserRequest request1 = new AddNewUserRequest();
        request1.setUserName("Austine Joy");
        request1.setEmailAddress("austinejoyz@gmail.com");
        request1.setPhoneNumber("09117474727");
        request1.setHomeAddress("sabo, yaba");
        assertThrows(EmailAlreadyExists.class,()->adminService.addNewUser(request1));
    }

    @Test
    public void testThatUsersDoNotHaveDuplicatePhoneNumbers() throws IOException {
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Austine Joy");
        request.setEmailAddress("austinejoyz@gmail.coms");
        request.setPhoneNumber("0909998876");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response = adminService.addNewUser(request);
        AddNewUserRequest request1 = new AddNewUserRequest();
        request1.setUserName("Austine Joy");
        request1.setEmailAddress("austinejoyz@gmail.com");
        request1.setPhoneNumber("0909998876");
        request1.setHomeAddress("sabo, yaba");
        assertThrows(PhoneNumberAlreadyExists.class,()->adminService.addNewUser(request1));
    }

    @Test
    public void testThatAnAdminCanAddANewBook(){
        AddBookRequest request = new AddBookRequest();
        request.setBookTitle("Beauty and the beast");
        request.setAuthor("buhari");
        request.setPublicationDate(LocalDate.now());
        request.setTotalNumber(50);
        request.setBookCategory(BookCategory.FANTASY);
        AddBookResponse response = adminService.addNewBookWith(request);
        assertThat(response.getMessage()).isEqualTo("New Book added successfully");
    }


    @Test
    public void testThatAdminCanDeleteAnExistingBook(){
        AddBookRequest request = new AddBookRequest();
        request.setBookTitle("Beauty and the beast");
        request.setAuthor("buhari");
        request.setPublicationDate(LocalDate.now());
        request.setTotalNumber(50);
        request.setBookCategory(BookCategory.FANTASY);
        AddBookResponse response = adminService.addNewBookWith(request);
        DeleteBookRequest request1 = new DeleteBookRequest();
        request1.setBookId(response.getBookId());
        DeleteBookResponse response1 = adminService.deleteExistingBook(request1);
        assertThat(response1.getMessage()).isEqualTo("Book successfully deleted");
    }

    @Test
    public void testThatAdminCanDeleteAnExistingUser() throws IOException {
        AddNewUserRequest request = new AddNewUserRequest();
        request.setUserName("Fareed Tijani");
        request.setEmailAddress("fareedtijani2810@gmail.com");
        request.setPhoneNumber("09117474727");
        request.setPassword("freddie");
        request.setHomeAddress("sabo, yaba");
        AddNewUserResponse response =  adminService.addNewUser(request);
        User user = userRepository.findUserByLibraryCardNumber(response.getLibraryNumber());
        DeleteUserRequest request1 = new DeleteUserRequest();
        request1.setUserId(user.getUserId());
        DeleteUserResponse response1 = adminService.deleteExistingUser(request1);
        assertThat(response1.getMessage()).isEqualTo("User successfully deleted");
    }

    @Test
    public void testThatAdminCanEditAnExistingBook(){
        AddBookRequest request = new AddBookRequest();
        request.setBookTitle("Beauty and the beast");
        request.setAuthor("buhari");
        request.setPublicationDate(LocalDate.now());
        request.setTotalNumber(50);
        request.setBookCategory(BookCategory.FANTASY);
        AddBookResponse response = adminService.addNewBookWith(request);
        EditExistingBookRequest request2 = new EditExistingBookRequest();
        request2.setBookId(response.getBookId());
        EditExistingBookResponse response1 = adminService.editExistingBook(request2);
        assertThat(response1.getMessage()).isEqualTo("Book updated successfully");
    }




}