package com.example.currencies.repository;

import com.example.currencies.model.CurrencyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRequestRepository extends JpaRepository<CurrencyRequest, Long> {
}