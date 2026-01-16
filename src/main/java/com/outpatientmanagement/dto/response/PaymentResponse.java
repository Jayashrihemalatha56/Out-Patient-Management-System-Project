package com.outpatientmanagement.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class PaymentResponse {

	private Long paymentId;
	private Double amount;
	private String paymentMode;
	private String paymentStatus;
	private LocalDate paymentDate;
	private LocalTime paymentTime;
	
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
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
	
}
