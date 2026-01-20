package com.outpatientmanagement.mapper;
import java.time.LocalDate;
import java.time.LocalTime;
 
import org.springframework.stereotype.Component;
 
import com.outpatientmanagement.dto.request.PaymentRequest;
import com.outpatientmanagement.dto.response.PaymentResponse;
import com.outpatientmanagement.entity.Appointment;
import com.outpatientmanagement.entity.Payment;
 
@Component
public class PaymentMapper {
 
    public Payment toEntity(PaymentRequest request, Appointment appointment) {
 
        Payment payment = new Payment();
        payment.setAppointment(appointment);
        payment.setAmount(request.getAmount());
        payment.setPaymentMode(request.getPaymentMode());
        payment.setPaymentStatus(request.getPaymentStatus());
        payment.setPaymentDate(
                request.getPaymentDate() != null ? request.getPaymentDate() : LocalDate.now()
        );
 
        return payment;
    }
 
    public PaymentResponse toResponse(Payment payment) {
 
        PaymentResponse response = new PaymentResponse();
        response.setPaymentId(payment.getPaymentId());
        response.setAmount(payment.getAmount());
        response.setPaymentMode(payment.getPaymentMode());
        response.setPaymentStatus(payment.getPaymentStatus());
        response.setPaymentDate(payment.getPaymentDate());
        response.setPaymentTime(LocalTime.now());
 
        return response;
    }
}
 