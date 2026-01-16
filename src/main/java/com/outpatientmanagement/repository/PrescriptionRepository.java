package com.outpatientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

}
