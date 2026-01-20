package com.outpatientmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.Patient;
import com.outpatientmanagement.entity.User;

public interface PatientRepository extends JpaRepository<Patient,Long>{

	Optional<Patient> findByUser(User user);

}
