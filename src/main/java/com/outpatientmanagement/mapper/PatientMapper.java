package com.outpatientmanagement.mapper;

import org.springframework.stereotype.Component;

import com.outpatientmanagement.dto.request.PatientRequest;
import com.outpatientmanagement.dto.response.PatientResponse;
import com.outpatientmanagement.entity.Patient;
import com.outpatientmanagement.entity.User;

@Component
public class PatientMapper {

	public Patient toRequest(PatientRequest request,User user)
	{
		Patient patient=new Patient();
		patient.setPatientName(request.getPatientName());
		patient.setAge(request.getAge());
		patient.setGender(request.getGender());
		patient.setPhoneNo(request.getPhoneNo());
		patient.setAddress(request.getAddress());
		patient.setSymptoms(request.getSymptoms());
		patient.setUser(user);
		
		return patient;	
	}
	
	public PatientResponse toResponse(Patient patient)
	{
		PatientResponse response=new PatientResponse();
		response.setName(patient.getPatientName());
		response.setAge(patient.getAge());
		response.setGender(patient.getGender());
		response.setPhone(patient.getPhoneNo());
		response.setAddress(patient.getAddress());
		response.setSymptoms(patient.getSymptoms());
		response.setUserId(patient.getUser().getId());
		
		return response;
	}
}
