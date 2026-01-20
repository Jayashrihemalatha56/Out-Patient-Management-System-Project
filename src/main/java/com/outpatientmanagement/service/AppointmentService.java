
package com.outpatientmanagement.service;

import org.springframework.http.ResponseEntity;

import com.outpatientmanagement.dto.request.AppointmentRequest;

public interface AppointmentService {

	ResponseEntity<?> createAppointment(AppointmentRequest request);

	ResponseEntity<?> getAppointmentById(Long id);

	ResponseEntity<?> getAllAppointments();

	ResponseEntity<?> updateAppointment(Long id, AppointmentRequest request);

	ResponseEntity<?> deleteAppointment(Long id);

	ResponseEntity<?> getAppointmentsByPatientId(Long patientId);

	ResponseEntity<?> getAppointmentsByDoctorId(Long doctorId);

	ResponseEntity<?> getAppointmentsByStatus(String status);

	ResponseEntity<?> cancelAppointment(Long id);

}
