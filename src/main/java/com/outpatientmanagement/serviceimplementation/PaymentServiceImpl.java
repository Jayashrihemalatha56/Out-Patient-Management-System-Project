package com.outpatientmanagement.serviceimplementation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outpatientmanagement.dto.request.PaymentRequest;
import com.outpatientmanagement.dto.response.PaymentResponse;
import com.outpatientmanagement.entity.Appointment;
import com.outpatientmanagement.entity.Payment;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.mapper.PaymentMapper;
import com.outpatientmanagement.repository.AppointmentRepository;
import com.outpatientmanagement.repository.PaymentRepository;
import com.outpatientmanagement.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
 
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
 
    private final PaymentRepository paymentRepository;
    private final AppointmentRepository appointmentRepository;
    private final PaymentMapper paymentMapper;
 
    public PaymentServiceImpl(PaymentRepository paymentRepository,
                              AppointmentRepository appointmentRepository,
                              PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.appointmentRepository = appointmentRepository;
        this.paymentMapper = paymentMapper;
    }
 
    @Override
    public ResponseEntity<?> createPayment(PaymentRequest request) {
 
        Appointment appointment = appointmentRepository
                .findById(request.getAppointmentId())
                .orElseThrow(() -> new ModelNotFoundException("Appointment not found"));
 
        Payment payment = paymentMapper.toEntity(request, appointment);
        paymentRepository.save(payment);
 
        return ResponseEntity.ok("Payment created successfully");
    }
 
    @Override
    public ResponseEntity<?> getPaymentById(Long paymentId) {
 
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ModelNotFoundException("Payment not found"));
 
        return ResponseEntity.ok(paymentMapper.toResponse(payment));
    }
 
    @Override
    public ResponseEntity<?> getAllPayments() {
 
        List<PaymentResponse> response =
                paymentRepository.findAll()
                        .stream()
                        .map(paymentMapper::toResponse)
                        .toList();
 
        return ResponseEntity.ok(response);
    }
 
    @Override
    public ResponseEntity<?> getPaymentsByAppointmentId(Long appointmentId) {
 
        List<PaymentResponse> response =
                paymentRepository.findByAppointment_AppointmentId(appointmentId)
                        .stream()
                        .map(paymentMapper::toResponse)
                        .toList();
 
        return ResponseEntity.ok(response);
    }
 
    @Override
    public ResponseEntity<?> getPaymentsByStatus(String status) {
 
        List<PaymentResponse> response =
                paymentRepository.findByPaymentStatus(status)
                        .stream()
                        .map(paymentMapper::toResponse)
                        .toList();
 
        return ResponseEntity.ok(response);
    }
 
    @Override
    public ResponseEntity<?> updatePaymentStatus(Long paymentId, String status) {
 
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ModelNotFoundException("Payment not found"));
 
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
 
        return ResponseEntity.ok("Payment status updated");
    }
}
 