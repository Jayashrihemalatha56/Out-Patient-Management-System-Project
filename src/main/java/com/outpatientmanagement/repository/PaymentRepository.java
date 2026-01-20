package com.outpatientmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.outpatientmanagement.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

	 	List<Payment> findByAppointment_AppointmentId(Long appointmentId);
	 
	    List<Payment> findByPaymentMode(String paymentMode);
	 
	    List<Payment> findByPaymentStatus(String paymentStatus);
	 
	    List<Payment> findByPaymentDate(LocalDate paymentDate);
	

}
