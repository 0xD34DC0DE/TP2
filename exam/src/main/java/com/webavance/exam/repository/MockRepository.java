package com.webavance.exam.repository;

import com.webavance.exam.model.Mock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MockRepository extends JpaRepository<Mock, String> {
    public Optional<Mock> findByEmail(String email);
}