package com.internship.oasis.oasistaskone.repositories;

import com.internship.oasis.oasistaskone.entities.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loans,Long> {
}
