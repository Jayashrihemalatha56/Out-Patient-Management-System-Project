package com.outpatientmanagement.service;

import org.springframework.http.ResponseEntity;

import com.outpatientmanagement.dto.request.PaymentRequest;

public interface PaymentService {

	ResponseEntity<?> createPayment(PaymentRequest request);

	ResponseEntity<?> getPaymentById(Long paymentId);

	ResponseEntity<?> getAllPayments();

	ResponseEntity<?> getPaymentsByAppointmentId(Long appointmentId);

	ResponseEntity<?> getPaymentsByStatus(String status);

	ResponseEntity<?> updatePaymentStatus(Long paymentId, String status);


}
