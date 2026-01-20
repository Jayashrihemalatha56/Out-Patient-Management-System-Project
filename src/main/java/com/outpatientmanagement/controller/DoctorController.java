package com.outpatientmanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
 
import com.outpatientmanagement.dto.request.DoctorRequest;
import com.outpatientmanagement.service.DoctorService;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/doctors")
public class DoctorController {
 
    private static final Logger logger =
            LoggerFactory.getLogger(DoctorController.class);
 
    private final DoctorService doctorService;
 
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
 
    @PostMapping("/create")
    public ResponseEntity<?> createDoctor(@Valid @RequestBody DoctorRequest request) {
        logger.info("Create doctor request received");
        return doctorService.createDoctor(request);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
        logger.info("Get doctor by id: {}", id);
        return doctorService.getDoctorById(id);
    }
 
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','DOCTOR')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllDoctors() {
        logger.info("Get all doctors");
        return doctorService.getAllDoctors();
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@Valid @PathVariable Long id,
                                          @RequestBody DoctorRequest request) {
        logger.info("Update doctor with id: {}", id);
        return doctorService.updateDoctor(id, request);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        logger.info("Delete doctor with id: {}", id);
        return doctorService.deleteDoctor(id);
    }
}

 