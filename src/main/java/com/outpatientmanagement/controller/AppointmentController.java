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

import com.outpatientmanagement.dto.request.AppointmentRequest;
import com.outpatientmanagement.service.AppointmentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
 
    private static final Logger logger =
            LoggerFactory.getLogger(AppointmentController.class);
 
    private final AppointmentService appointmentService;
 
    public AppointmentController(
            AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }
 
    @PreAuthorize("hasRole('PATIENT')")
    @PostMapping("/create")
    public ResponseEntity<?> createAppointment(@Valid @RequestBody AppointmentRequest request) {
 
        logger.info("Create appointment request");
        return appointmentService.createAppointment(request);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
 
        return appointmentService.getAppointmentById(id);
    }
 
    @PreAuthorize("hasAnyAuthority('ADMIN','PATIENT','DOCTOR')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllAppointments() {
 
        return appointmentService.getAllAppointments();
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(
    		@Valid @PathVariable Long id,
            @RequestBody AppointmentRequest request) {
 
        return appointmentService.updateAppointment(id, request);
    }
 
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(
    		@PathVariable Long id) {
 
        return appointmentService.deleteAppointment(id);
    }
 
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<?> getByPatient(
    		@Valid @PathVariable Long patientId) {
 
        return appointmentService.getAppointmentsByPatientId(patientId);
    }
 
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<?> getByDoctor(
    		@Valid @PathVariable Long doctorId) {
 
        return appointmentService.getAppointmentsByDoctorId(doctorId);
    }
 
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getByStatus(
    		@Valid @PathVariable String status) {
 
        return appointmentService.getAppointmentsByStatus(status);
    }
 
    @PutMapping("/cancel/{id}")
    public ResponseEntity<?> cancelAppointment(
    		@Valid @PathVariable Long id) {
 
        return appointmentService.cancelAppointment(id);
    }
}
 