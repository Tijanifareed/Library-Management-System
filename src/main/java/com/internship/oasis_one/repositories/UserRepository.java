package com.internship.oasis_one.repositories;

import com.internship.oasis_one.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
