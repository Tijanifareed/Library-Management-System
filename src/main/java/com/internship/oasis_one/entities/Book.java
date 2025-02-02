package com.internship.oasis_one.entities;


import jakarta.persistence.*;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    @Enumerated(EnumType.STRING)
    private BooksCategories booksCategory;
    private String Author;
    private int availableCopies;
}
