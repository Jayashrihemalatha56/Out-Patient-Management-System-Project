package com.outpatientmanagement.serviceimplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outpatientmanagement.dto.request.DoctorRequest;
import com.outpatientmanagement.dto.response.DoctorResponse;
import com.outpatientmanagement.entity.Doctor;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.mapper.DoctorMapper;
import com.outpatientmanagement.repository.DoctorRepository;
import com.outpatientmanagement.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {
 
    private static final Logger logger =
            LoggerFactory.getLogger(DoctorServiceImpl.class);
 
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
 
    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             DoctorMapper doctorMapper) {
        this.doctorRepository = doctorRepository;
        this.doctorMapper = doctorMapper;
    }
 
    @Override
    public ResponseEntity<?> createDoctor(DoctorRequest request) {
        logger.info("Creating doctor profile");
 
        Doctor doctor = doctorMapper.toRequest(request);
        Doctor savedDoctor = doctorRepository.save(doctor);
 
        DoctorResponse response = doctorMapper.toResponse(savedDoctor);
        return ResponseEntity.ok(response);
    }
 
    @Override
    public ResponseEntity<?> getDoctorById(Long id) {
        logger.info("Fetching doctor with id: {}", id);
 
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Doctor not found"));
 
        return ResponseEntity.ok(doctorMapper.toResponse(doctor));
    }
 
    @Override
    public ResponseEntity<?> getAllDoctors() {
        logger.info("Fetching all doctors");
 
        List<DoctorResponse> responses = doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toResponse)
                .collect(Collectors.toList());
 
        return ResponseEntity.ok(responses);
    }
 
    @Override
    public ResponseEntity<?> updateDoctor(Long id, DoctorRequest request) {
        logger.info("Updating doctor with id: {}", id);
 
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Update Failed"));
 
        doctor.setDoctorName(request.getDoctorName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setPhone(request.getPhoneNo());
        doctor.setEmail(request.getEmail());
 
        Doctor updatedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(doctorMapper.toResponse(updatedDoctor));
    }
 
    @Override
    public ResponseEntity<?> deleteDoctor(Long id) {
        logger.info("Deleting doctor with id: {}", id);
 
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Deletion Failed"));
 
        doctorRepository.delete(doctor);
        return ResponseEntity.ok("Doctor deleted successfully");
    }

}
 
