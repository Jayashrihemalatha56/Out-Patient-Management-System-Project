package com.outpatientmanagement.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
 
import com.outpatientmanagement.dto.request.PaymentRequest;
import com.outpatientmanagement.service.PaymentService;

import jakarta.validation.Valid;
 
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
 
    private final PaymentService paymentService;
 
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
 
    @PostMapping
    public ResponseEntity<?> createPayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.createPayment(request);
    }
 
    @GetMapping("/{paymentId}")
    public ResponseEntity<?> getPaymentById(@PathVariable Long paymentId) {
        return paymentService.getPaymentById(paymentId);
    }
 
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT','DOCTOR')")
    @GetMapping("/all")
    public ResponseEntity<?> getAllPayments() {
        return paymentService.getAllPayments();
    }
 
    @GetMapping("/appointment/{appointmentId}")
    public ResponseEntity<?> getPaymentsByAppointment(@Valid @PathVariable Long appointmentId) {
        return paymentService.getPaymentsByAppointmentId(appointmentId);
    }
 
    @GetMapping("/status/{status}")
    public ResponseEntity<?> getPaymentsByStatus(@Valid @PathVariable String status) {
        return paymentService.getPaymentsByStatus(status);
    }
 
    @PutMapping("/{paymentId}/status/{status}")
    public ResponseEntity<?> updatePaymentStatus(@Valid @PathVariable Long paymentId,
                                                 @PathVariable String status) {
        return paymentService.updatePaymentStatus(paymentId, status);
    }
 
}
 
