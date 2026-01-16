package com.outpatientmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

}
