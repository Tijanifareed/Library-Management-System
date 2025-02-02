package com.internship.oasis.oasistaskone.repositories;

import com.internship.oasis.oasistaskone.entities.Book;
import com.internship.oasis.oasistaskone.entities.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {


    Optional<Book> findByTitle(String title);

    List<Book> findBookByBookCategory(BookCategory bookCategory);
}
