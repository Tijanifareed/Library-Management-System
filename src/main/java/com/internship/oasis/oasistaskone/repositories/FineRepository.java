package com.internship.oasis.oasistaskone.repositories;

import com.internship.oasis.oasistaskone.entities.Fines;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FineRepository extends JpaRepository<Fines, Long> {
}
