package com.outpatientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {

}
