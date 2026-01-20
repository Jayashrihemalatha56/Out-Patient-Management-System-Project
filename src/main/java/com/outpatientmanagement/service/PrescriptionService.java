package com.outpatientmanagement.service;

import org.springframework.http.ResponseEntity;

import com.outpatientmanagement.dto.request.PrescriptionRequest;

public interface PrescriptionService {

	ResponseEntity<?> createPrescription(PrescriptionRequest request);

	ResponseEntity<?> getPrescriptionById(Long id);

	ResponseEntity<?> getAllPrescriptions();

	ResponseEntity<?> deletePrescription(Long id);

	ResponseEntity<?> getPrescriptionsByAppointmentId(Long appointmentId);

	ResponseEntity<?> getPrescriptionsByDoctorName(String doctorName);

	ResponseEntity<?> getPrescriptionsByDate(String date);

	ResponseEntity<?> checkPrescriptionExistsForAppointment(Long appointmentId);

}
