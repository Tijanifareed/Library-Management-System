package com.internship.oasis.oasistaskone.services.admin;


import com.internship.oasis.oasistaskone.dtos.requests.*;
import com.internship.oasis.oasistaskone.dtos.responses.*;
import com.internship.oasis.oasistaskone.entities.*;
import com.internship.oasis.oasistaskone.exceptions.BookNotFoundExeption;
import com.internship.oasis.oasistaskone.exceptions.EmailAlreadyExists;
import com.internship.oasis.oasistaskone.exceptions.PhoneNumberAlreadyExists;
import com.internship.oasis.oasistaskone.exceptions.UserNotFoundException;
import com.internship.oasis.oasistaskone.repositories.BookRepository;
import com.internship.oasis.oasistaskone.repositories.FineRepository;
import com.internship.oasis.oasistaskone.repositories.LoanRepository;
import com.internship.oasis.oasistaskone.repositories.UserRepository;
import com.internship.oasis.oasistaskone.dtos.responses.DeleteBookResponse;
import com.internship.oasis.oasistaskone.services.image.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    private static final double FINE_PER_DAY = 100;

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;


    @Autowired
    private ImageService imageService;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private FineRepository fineRepository;


    @Override
    public AddNewUserResponse addNewUser(AddNewUserRequest request) throws IOException {
        validateRequest(request);
        User user = new User();
        user.setUserName(request.getUserName());
        user.setEmailAddress(request.getEmailAddress());
        user.setLibraryCardNumber(generateLibraryNumber());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        user.setHomeAddress(request.getHomeAddress());
        user.setUserRole(UserRole.CUSTOMER);

//        String profilePictureUrl = imageService.uploadImage(request.getProfilePicture());
//        user.setProfilePicture(profilePictureUrl);
        userRepository.save(user);
        AddNewUserResponse response = new AddNewUserResponse();
        response.setMessage("New User Created Successfully");
        response.setLibraryNumber(user.getLibraryCardNumber());
        return response;
    }

    @Override
    public AddBookResponse addNewBookWith(AddBookRequest request) {
        Book book = new Book();
        book.setTitle(request.getBookTitle());
        book.setAuthor(request.getAuthor());
        book.setTotalNumber(request.getTotalNumber());
        book.setBookCategory(request.getBookCategory());
        book.setAvaliableCopies(request.getTotalNumber());
        book.setPublicationDate(request.getPublicationDate());
        bookRepository.save(book);
        AddBookResponse response = new AddBookResponse();
        response.setBookId(book.getBookId());
        response.setMessage("New Book added successfully");
        return response;
    }

    @Override
    public DeleteBookResponse deleteExistingBook(DeleteBookRequest request1) {
        List<Book> books = bookRepository.findAll();
        for (Book book : books)if (book.getBookId() == request1.getBookId()) {
                books.remove(book);
                bookRepository.saveAll(books);
                DeleteBookResponse response = new DeleteBookResponse();
                response.setMessage("Book successfully deleted");
                return response;
                                }

        throw new BookNotFoundExeption("Book not found. Check the spelling.");

    }

    @Override
    public DeleteUserResponse deleteExistingUser(DeleteUserRequest request1) {
        List<User> users = userRepository.findAll();
        for (User user : users)if (user.getUserId() == request1.getUserId()) {
            users.remove(user);
            userRepository.saveAll(users);
            DeleteUserResponse response = new DeleteUserResponse();
            response.setMessage("User successfully deleted");
            return response;
        }

        throw new BookNotFoundExeption("User not found. Check the spelling.");

    }

    @Override
    public EditExistingBookResponse editExistingBook(EditExistingBookRequest request2) {
        Optional<Book> bookOptional = bookRepository.findById(request2.getBookId());
        if (bookOptional.isEmpty()) {
            throw new BookNotFoundExeption("Book with ID " + request2.getBookId() + " not found");
        }
        Book book = bookOptional.get();
        if (request2.getTitle() != null && !request2.getTitle().isEmpty()) {
            book.setTitle(request2.getTitle());
        }
        if (request2.getAuthor() != null && !request2.getAuthor().isEmpty()) {
            book.setAuthor(request2.getAuthor());
        }
        if (request2.getBookCategory() != null && !request2.getBookCategory().toString().isEmpty()) {
            book.setBookCategory(request2.getBookCategory());
        }
        if (request2.getAvaliableCopies() != 0) {
            book.setAvaliableCopies(request2.getAvaliableCopies());
        }
        bookRepository.save(book);
        EditExistingBookResponse response = new EditExistingBookResponse();
        response.setBookId(book.getBookId());
        response.setMessage("Book updated successfully");
        return response;
    }

    @Override
    public EditExistingUserResponse editExistingUser(EditExistingUserRequest request) {
        Optional<User> userOptional = userRepository.findById(request.getUserId());
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with ID " + request.getUserId() + " not found");
        }
        User user = userOptional.get();
        if (request.getUserName() != null && !request.getUserName().isEmpty()) {
            user.setUserName(request.getUserName());
        }
        if (request.getEmailAddress() != null && !request.getEmailAddress().isEmpty()) {
            user.setEmailAddress(request.getEmailAddress());
        }
        if (request.getLibraryCardNumber() > 0) {
            user.setLibraryCardNumber(request.getLibraryCardNumber());
        }
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if (request.getHomeAddress() != null && !request.getHomeAddress().isEmpty()) {
            user.setHomeAddress(request.getHomeAddress());
        }
        if (request.getProfilePicture() != null && !request.getProfilePicture().isEmpty()) {
            user.setProfilePicture(request.getProfilePicture());
        }
        if (request.getUserRole() != null) {
            user.setUserRole(request.getUserRole());
        }
        userRepository.save(user);
        EditExistingUserResponse response = new EditExistingUserResponse();
        response.setUserId(user.getUserId());
        response.setMessage("User updated successfully");
        return response;
    }

    @Override
    public FindUserResponse findUserById(FindUserRequest request) {
        Optional<User> user = userRepository.findById(request.getUserId());
        if(user.isEmpty()) throw new UserNotFoundException("User not found try again later");
        FindUserResponse response = new FindUserResponse();
        response.setUser(user.get());
        return response;
    }

    @Override
    public GetDebtorsResponse getDebtors() {
        List<Loans> loans = loanRepository.findAll();
        List<Loans> dueLoan = new ArrayList<>();
        for (Loans loan : loans){
            if(loan.getDueDate().isBefore(LocalDate.now()) && Objects.equals(loan.getBookStatus(), "Borrowed")){
                 dueLoan.add(loan);
            }
        }
        GetDebtorsResponse response = new GetDebtorsResponse();
        response.setNotReturnedLoan(dueLoan);
        return response;
    }

    @Override
    public ViewFinesResponse viewFinesForOverdueReturns() {
        List<Loans> loans = loanRepository.findAll();
        if (loans.isEmpty()) throw new BookNotFoundExeption("There are no loaned book");
        ViewFinesResponse response = new ViewFinesResponse();
        response.setLoans(loans);
        return response;
    }

    @Override
    public VerifyUserPayment verifyUserPayment(Long fineId) {
        List<Fines> fines = fineRepository.findAll();
        for (Fines fine: fines){
            if (fine.getFineId() == fineId){
                fine.setDatePaid(LocalDate.now());
                fine.setPaid(true);
                VerifyUserPayment payment = new VerifyUserPayment();
                payment.setMessage("Fine Paid successfully");
                return payment;
            }
        }
            throw new BookNotFoundExeption("No Such fine");
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void calculateFinesForOverdueBooks() {
        List<Loans> loans = loanRepository.findAll();
        loans.stream()
                .filter(loan -> !loan.getBookStatus().equals("Returned") && loan.getDueDate().isBefore(LocalDate.now()))
                .forEach(loan ->{
                    long overdueDays = ChronoUnit.DAYS.between(loan.getDueDate(), LocalDate.now());
                    double fine = overdueDays * FINE_PER_DAY;
                    Fines fines = new Fines();
                    fines.setAmount(fine);
                    fines.setLoanId(loan.getLoanId());
                    fines.setDueDate(loan.getDueDate());
                    fines.setPaid(false);
                    fineRepository.save(fines);

        });

    }


    public void validateRequest(AddNewUserRequest request){
        User user1 = userRepository.findUserByEmailAddress(request.getEmailAddress());
        User user = userRepository.findUserByPhoneNumber(request.getPhoneNumber());
        if(user1 != null)throw new EmailAlreadyExists("A user with this email address already exists");
        else if(user != null) throw new PhoneNumberAlreadyExists("A user with this phone number already exists");
    }

    public int generateLibraryNumber(){
        Random random = new Random();
        int randomNumber = 1000 * random.nextInt(9000);
        User user = userRepository.findUserByLibraryCardNumber(randomNumber);
        if(user != null){
            generateLibraryNumber();
        }
        return randomNumber;

    }

}
