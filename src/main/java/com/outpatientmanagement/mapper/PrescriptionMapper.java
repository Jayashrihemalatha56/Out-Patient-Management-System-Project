package com.outpatientmanagement.mapper;
import org.springframework.stereotype.Component;
 
import com.outpatientmanagement.dto.request.PrescriptionRequest;
import com.outpatientmanagement.dto.response.PrescriptionResponse;
import com.outpatientmanagement.entity.Appointment;
import com.outpatientmanagement.entity.Prescription;
 
@Component
public class PrescriptionMapper {
 
    public Prescription toEntity(
            PrescriptionRequest request,
            Appointment appointment) {
 
        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setDoctorName(request.getDoctorName());
        prescription.setMedicines(request.getMedicines());
        prescription.setNotes(request.getNotes());
        prescription.setCreatedDate(java.time.LocalDate.now());
        prescription.setCreatedTime(java.time.LocalTime.now());
 
        return prescription;
    }
 
    public PrescriptionResponse toResponse(Prescription prescription) {
 
        PrescriptionResponse response = new PrescriptionResponse();
        response.setPrescriptionId(prescription.getPrescriptionId());
        response.setDoctorName(prescription.getDoctorName());
        response.setMedicines(prescription.getMedicines());
        response.setNotes(prescription.getNotes());
        response.setCreatedDate(prescription.getCreatedDate());
        response.setCreatedTime(prescription.getCreatedTime());
 
        return response;
    }
}
 