package com.outpatientmanagement.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="payments")
public class Payment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long paymentId;
	
	@OneToOne
	@JoinColumn(name="appointment_id")
	private Appointment appointment;
	
	private Double amount;
	
	private String paymentMode;
	
	private String paymentStatus;
	
	private LocalDate paymentDate;
	
	private LocalTime paymentTime;

	public Payment() {
		super();
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public LocalTime getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(LocalTime paymentTime) {
		this.paymentTime = paymentTime;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", appointment=" + appointment + ", amount=" + amount
				+ ", paymentMode=" + paymentMode + ", paymentStatus=" + paymentStatus + ", paymentDate=" + paymentDate
				+ "]";
	}
	
}
