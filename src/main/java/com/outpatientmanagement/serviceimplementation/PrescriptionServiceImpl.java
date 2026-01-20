package com.outpatientmanagement.serviceimplementation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.outpatientmanagement.dto.request.PrescriptionRequest;
import com.outpatientmanagement.dto.response.PrescriptionResponse;
import com.outpatientmanagement.entity.Appointment;
import com.outpatientmanagement.entity.Prescription;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.mapper.PrescriptionMapper;
import com.outpatientmanagement.repository.AppointmentRepository;
import com.outpatientmanagement.repository.PrescriptionRepository;
import com.outpatientmanagement.service.PrescriptionService;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {
 
    private static final Logger logger = LoggerFactory.getLogger(PrescriptionServiceImpl.class);
 
    private final PrescriptionRepository prescriptionRepository;
    private final AppointmentRepository appointmentRepository;
    private final PrescriptionMapper prescriptionMapper;
 
    public PrescriptionServiceImpl(PrescriptionRepository prescriptionRepository,
                                   AppointmentRepository appointmentRepository,
                                   PrescriptionMapper prescriptionMapper) {
        this.prescriptionRepository = prescriptionRepository;
        this.appointmentRepository = appointmentRepository;
        this.prescriptionMapper = prescriptionMapper;
    }
 
    @Override
    public ResponseEntity<?> createPrescription(PrescriptionRequest request) {
 
        logger.info("Creating prescription for appointmentId {}", request.getAppointmentId());
 
        Appointment appointment = appointmentRepository.findById(
                request.getAppointmentId()
        ).orElseThrow(() -> new RuntimeException("Appointment not found"));
         
        Prescription prescription = new Prescription();
        prescription.setAppointment(appointment);
        prescription.setDoctorName(request.getDoctorName());
        prescription.setDiagnosis(request.getDiagnosis());
        prescription.setMedicines(request.getMedicines());
        prescription.setNotes(request.getNotes());
         
        Prescription saved = prescriptionRepository.save(prescription);
         
        return ResponseEntity.ok("Prescription created successfully");
    }
 
    @Override
    public ResponseEntity<?> getPrescriptionById(Long id) {
 
        Prescription prescription = prescriptionRepository
                .findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Prescription not found"));
 
        return ResponseEntity.ok(
                prescriptionMapper.toResponse(prescription)
        );
    }
 
    @Override
    public ResponseEntity<?> getAllPrescriptions() {
 
        List<PrescriptionResponse> response =
                prescriptionRepository.findAll()
                        .stream()
                        .map(prescriptionMapper::toResponse)
                        .collect(Collectors.toList());
 
        return ResponseEntity.ok(response);
    }
 
    @Override
    public ResponseEntity<?> deletePrescription(Long id) {
 
        prescriptionRepository.deleteById(id);
        return ResponseEntity.ok("Prescription deleted successfully");
    }
 
    @Override
    public ResponseEntity<?> getPrescriptionsByAppointmentId(Long appointmentId) {
 
        List<PrescriptionResponse> response =
                prescriptionRepository.findByAppointment_AppointmentId(appointmentId)
                        .stream()
                        .map(prescriptionMapper::toResponse)
                        .collect(Collectors.toList());
 
        return ResponseEntity.ok(response);
    }
 
    @Override
    public ResponseEntity<?> getPrescriptionsByDoctorName(String doctorName) {
 
        List<PrescriptionResponse> response =
                prescriptionRepository.findByAppointment_Doctor_DoctorName(doctorName)
                        .stream()
                        .map(prescriptionMapper::toResponse)
                        .collect(Collectors.toList());
 
        return ResponseEntity.ok(response);
    }
 
    @Override
    public ResponseEntity<?> getPrescriptionsByDate(String date) {
 
        LocalDate localDate = LocalDate.parse(date);
 
        List<PrescriptionResponse> response =
                prescriptionRepository.findByCreatedDate(localDate)
                        .stream()
                        .map(prescriptionMapper::toResponse)
                        .collect(Collectors.toList());
 
        return ResponseEntity.ok(response);
    }
 
    @Override
    public ResponseEntity<?> checkPrescriptionExistsForAppointment(Long appointmentId) {
 
        boolean exists =
                prescriptionRepository.existsByAppointment_AppointmentId(appointmentId);
 
        return ResponseEntity.ok(exists);
    }
}
 
