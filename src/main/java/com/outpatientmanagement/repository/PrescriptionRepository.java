package com.outpatientmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {

	    List<Prescription> findByAppointment_AppointmentId(Long appointmentId);

	    List<Prescription> findByAppointment_Doctor_DoctorName(String doctorName);

	    List<Prescription> findByCreatedDate(LocalDate createdDate);

	    boolean existsByAppointment_AppointmentId(Long appointmentId);
}