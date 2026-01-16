package com.outpatientmanagement.dto.response;

import java.time.LocalDate;
import java.time.LocalTime;

public class PrescriptionResponse {

	private Long prescriptionId;
	private String doctorName;
	private String medicines;
	private String notes;
	private LocalDate createdDate;
	private LocalTime createdTime;
	
	public Long getPrescriptionId() {
		return prescriptionId;
	}
	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getMedicines() {
		return medicines;
	}
	public void setMedicines(String medicines) {
		this.medicines = medicines;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalTime getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(LocalTime createdTime) {
		this.createdTime = createdTime;
	}
		
}
