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
@Table(name="prescriptions")
public class Prescription {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long prescriptionId;
	
	@OneToOne
	@JoinColumn(name="appointment_id")
	private Appointment appointment;
	
	private String medicines;
	
	private String notes;
	
	private String doctorName;
	
	private LocalDate createdDate;
	
	private LocalTime createdTime;

	public Prescription() {
		super();
	}

	public Long getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Long prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
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

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", appointment=" + appointment + ", medicines="
				+ medicines + ", notes=" + notes + ", doctorName=" + doctorName + ", createdDate=" + createdDate
				+ ", createdTime=" + createdTime + "]";
	}

}
