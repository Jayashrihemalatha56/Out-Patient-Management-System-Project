package com.outpatientmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outpatientmanagement.dto.request.PatientRequest;
import com.outpatientmanagement.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

	private static final Logger logger=LoggerFactory.getLogger(PatientController.class);
	
	private final PatientService patientService;

	public PatientController(PatientService patientService) {
		super();
		this.patientService = patientService;
	}
	@PostMapping("/create")
    public ResponseEntity<?> createPatient(@Valid @RequestBody PatientRequest request) {
        logger.info("Creating patient with name: {}", request.getPatientName());
 
        ResponseEntity<?> response = patientService.createPatient(request);
 
        logger.info("Patient created successfully");
 
        return response;
    }
	@GetMapping("/{id}")
	public ResponseEntity<?> getPatientById(@PathVariable Long id) {
		logger.info("Fetching patient with id: ", id);
		return patientService.getPatientById(id);
	}

	@PreAuthorize("hasAnyAuthority('PATIENT')")
	@GetMapping("/all")
	public ResponseEntity<?> getAllPatients() {
		logger.info("Fetching all patients");
		return patientService.getAllPatients();
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updatePatient(@Valid @PathVariable Long id, @RequestBody PatientRequest request) {
		logger.info("Updating patient with id: ", id);
		return patientService.updatePatient(id, request);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePatient(@PathVariable Long id) {
		logger.info("Deleting patient with id: ", id);
		return patientService.deletePatient(id);
	}
}