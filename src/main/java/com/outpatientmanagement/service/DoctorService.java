package com.outpatientmanagement.service;

import org.springframework.http.ResponseEntity;

import com.outpatientmanagement.dto.request.DoctorRequest;

public interface DoctorService {

	ResponseEntity<?> createDoctor(DoctorRequest request);

	ResponseEntity<?> getDoctorById(Long id);

	ResponseEntity<?> getAllDoctors();

	ResponseEntity<?> updateDoctor(Long id, DoctorRequest request);

	ResponseEntity<?> deleteDoctor(Long id);

}
