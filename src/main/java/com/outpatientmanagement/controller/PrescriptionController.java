package com.outpatientmanagement.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.outpatientmanagement.dto.request.PrescriptionRequest;
import com.outpatientmanagement.service.PrescriptionService;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
 
    private final PrescriptionService prescriptionService;
 
    public PrescriptionController(PrescriptionService prescriptionService) {
        this.prescriptionService = prescriptionService;
    }
    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PrescriptionRequest request) {
        return prescriptionService.createPrescription(request);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return prescriptionService.getPrescriptionById(id);
    }
 
    @GetMapping("/all")
    public ResponseEntity<?> getAll() {
        return prescriptionService.getAllPrescriptions();
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return prescriptionService.deletePrescription(id);
    }
 
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','DOCTOR')")
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<?> getByAppointment(@Valid @PathVariable Long appointmentId) {
        return prescriptionService.getPrescriptionsByAppointmentId(appointmentId);
    }
 
    @GetMapping("/doctor/{doctorName}")
    public ResponseEntity<?> getByDoctor(@Valid @PathVariable String doctorName) {
        return prescriptionService.getPrescriptionsByDoctorName(doctorName);
    }
 
    @GetMapping("/date/{date}")
    public ResponseEntity<?> getByDate(@Valid @PathVariable String date) {
        return prescriptionService.getPrescriptionsByDate(date);
    }
 
    @GetMapping("/exists/{appointmentId}")
    public ResponseEntity<?> exists(@Valid @PathVariable Long appointmentId) {
        return prescriptionService.checkPrescriptionExistsForAppointment(appointmentId);
    }
}
 
