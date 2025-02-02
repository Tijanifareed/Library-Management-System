package com.internship.oasis.oasistaskone.repositories;

import com.internship.oasis.oasistaskone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByLibraryCardNumber(int libraryCardNumber);

    User findUserByUserName(String userName);

    User findUserByEmailAddress(String emailAddress);

    User findUserByPhoneNumber(String phoneNumber);
}
