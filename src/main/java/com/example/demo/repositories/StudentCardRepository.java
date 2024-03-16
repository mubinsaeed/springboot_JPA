package com.example.demo.repositories;

import com.example.demo.entities.StudentIdCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCardRepository extends JpaRepository<StudentIdCard,Long> {
}
