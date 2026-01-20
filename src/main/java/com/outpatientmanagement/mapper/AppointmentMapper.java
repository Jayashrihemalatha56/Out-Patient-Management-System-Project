package com.outpatientmanagement.mapper;

import org.springframework.stereotype.Component;
 
import com.outpatientmanagement.dto.request.AppointmentRequest;
import com.outpatientmanagement.dto.response.AppointmentResponse;
import com.outpatientmanagement.entity.Appointment;
import com.outpatientmanagement.entity.Doctor;
import com.outpatientmanagement.entity.Patient;
 
@Component
public class AppointmentMapper {
 
    public Appointment toRequest(AppointmentRequest request,
                                Patient patient,
                                Doctor doctor) {
 
        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
 
        return appointment;
    }
 
    public AppointmentResponse toResponse(Appointment appointment) {
 
        AppointmentResponse response = new AppointmentResponse();
        response.setAppointmentId(appointment.getAppointmentId());
        if (appointment.getPatient() != null) {
            response.setPatientId(
                appointment.getPatient().getPatientId()
            );
        }
     
        if (appointment.getDoctor() != null) {
            response.setDoctorId(
                appointment.getDoctor().getDoctorId()
            );
        }
        response.setAppointmentDate(appointment.getAppointmentDate());
        response.setAppointmentTime(appointment.getAppointmentTime());
        response.setStatus(appointment.getStatus());
 
        return response;
    }
}
 