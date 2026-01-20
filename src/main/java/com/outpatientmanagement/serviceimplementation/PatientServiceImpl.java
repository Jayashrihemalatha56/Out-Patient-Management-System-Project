package com.outpatientmanagement.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.outpatientmanagement.dto.request.PatientRequest;
import com.outpatientmanagement.dto.response.PatientResponse;
import com.outpatientmanagement.entity.Patient;
import com.outpatientmanagement.entity.User;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.mapper.PatientMapper;
import com.outpatientmanagement.repository.PatientRepository;
import com.outpatientmanagement.repository.UserRepository;
import com.outpatientmanagement.service.PatientService;
 
@Service
public class PatientServiceImpl implements PatientService {
 
	private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

	private final PatientRepository patientRepository;
	private final UserRepository userRepository;
	private final PatientMapper patientMapper;

	public PatientServiceImpl(PatientRepository patientRepository, UserRepository userRepository,
			PatientMapper patientMapper) {

		this.patientRepository = patientRepository;
		this.userRepository = userRepository;
		this.patientMapper = patientMapper;
    }
 
	@Override
	public ResponseEntity<?> createPatient(PatientRequest request) {
	 
	    Authentication authentication =
	            SecurityContextHolder.getContext().getAuthentication();
	 
	    if (authentication == null ||
	        !authentication.isAuthenticated() ||
	        authentication.getName().equals("anonymousUser")) {
	 
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body("User not authenticated");
	    }
	 
	    String username = authentication.getName();
	 
	    User user = userRepository.findByUsername(username)
	            .orElseThrow(() -> new ModelNotFoundException("User not found"));
	 
	    Patient patient = patientMapper.toRequest(request, user);
	 
	    patientRepository.save(patient);
	 
	    return ResponseEntity
	            .status(HttpStatus.CREATED)
	            .body("Patient profile created successfully");
	}
	 
	 
    @Override
    public ResponseEntity<?> getPatientById(Long id) {
 
        logger.info("Fetching patient with id: {}", id);
 
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Patient id not found"));
 
        return ResponseEntity.ok(
                patientMapper.toResponse(patient)
        );
    }
 
    @Override
    public ResponseEntity<?> getAllPatients() {
 
        logger.info("Fetching all patients");
 
        List<PatientResponse> response =
                patientRepository.findAll()
                        .stream()
                        .map(patientMapper::toResponse)
                        .collect(Collectors.toList());
 
        return ResponseEntity.ok(response);
    }
 
    @Override
    public ResponseEntity<?> updatePatient(Long id, PatientRequest request) {
 
        logger.info("Updating patient with id: {}", id);
 
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Patient updation failed"));
 
        patient.setPatientName(request.getPatientName());
        patient.setAge(request.getAge());
        patient.setGender(request.getGender());
        patient.setPhoneNo(request.getPhoneNo());
        patient.setAddress(request.getAddress());
        patient.setSymptoms(request.getSymptoms());
 
        Patient updatedPatient = patientRepository.save(patient);
 
        return ResponseEntity.ok(
                patientMapper.toResponse(updatedPatient)
        );
    }
 
    @Override
    public ResponseEntity<?> deletePatient(Long id) {
 
        logger.info("Deleting patient with id: {}", id);
 
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Patient not found"));
 
        patientRepository.delete(patient);
 
        return ResponseEntity.ok("Patient deleted successfully");
    }

	
}
 