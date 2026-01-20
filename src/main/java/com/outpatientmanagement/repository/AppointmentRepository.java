package com.outpatientmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	  	List<Appointment> findByPatient_PatientId(Long patientId);
	  
	    List<Appointment> findByDoctor_DoctorId(Long doctorId);
	 
	    List<Appointment> findByStatus(String status);

	

}
