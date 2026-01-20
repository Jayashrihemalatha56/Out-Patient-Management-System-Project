package com.outpatientmanagement.service;

import org.springframework.http.ResponseEntity;

import com.outpatientmanagement.dto.request.PatientRequest;

public interface PatientService {

	ResponseEntity<?> createPatient(PatientRequest request);

	ResponseEntity<?> getPatientById(Long id);
	
	ResponseEntity<?> getAllPatients();

	ResponseEntity<?> updatePatient(Long id, PatientRequest request);

	ResponseEntity<?> deletePatient(Long id);

	


}
