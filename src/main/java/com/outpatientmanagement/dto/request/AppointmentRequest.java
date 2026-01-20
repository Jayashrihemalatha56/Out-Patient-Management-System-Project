package com.outpatientmanagement.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AppointmentRequest {
 
    @NotNull(message = "Doctor ID is required")
    private Long doctorId;
 
    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be today or future")
    private LocalDate appointmentDate;
 
    @NotNull(message = "Appointment time is required")
    private LocalTime appointmentTime;
 
	
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public LocalDate getAppointmentDate() {
		return appointmentDate;
	}
	public void setAppointmentDate(LocalDate appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	public LocalTime getAppointmentTime() {
		return appointmentTime;
	}
	public void setAppointmentTime(LocalTime appointmentTime) {
		this.appointmentTime = appointmentTime;
	}
	
	
}
