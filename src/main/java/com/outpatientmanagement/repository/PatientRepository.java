package com.outpatientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{

}
