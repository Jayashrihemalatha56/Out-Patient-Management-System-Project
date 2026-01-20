package com.outpatientmanagement.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.outpatientmanagement.dto.request.DoctorRequest;
import com.outpatientmanagement.dto.response.DoctorResponse;
import com.outpatientmanagement.entity.Doctor;
import com.outpatientmanagement.entity.User;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.repository.UserRepository;
 
@Component
public class DoctorMapper {
	
	@Autowired
	private UserRepository userRepo;
 
    public Doctor toRequest(DoctorRequest request) {
        Doctor doctor = new Doctor();
        doctor.setDoctorName(request.getDoctorName());
        doctor.setSpecialization(request.getSpecialization());
        doctor.setPhone(request.getPhoneNo());
        doctor.setEmail(request.getEmail());
        doctor.setAvailable(request.getAvailable());
        
        User user=userRepo.findById(request.getUserId())
        		.orElseThrow(()->new ModelNotFoundException("User Id not found"));
        doctor.setUser(user);
        		return doctor;
    }
 
    public DoctorResponse toResponse(Doctor doctor) {
        DoctorResponse response = new DoctorResponse();
        response.setDoctorId(doctor.getDoctorId());
        response.setName(doctor.getDoctorName());
        response.setSpecialization(doctor.getSpecialization());
        response.setPhone(doctor.getPhone());
        response.setEmail(doctor.getEmail());
        return response;
    }
}
 
