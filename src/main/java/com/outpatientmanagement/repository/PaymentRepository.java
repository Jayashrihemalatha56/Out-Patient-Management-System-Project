package com.outpatientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
