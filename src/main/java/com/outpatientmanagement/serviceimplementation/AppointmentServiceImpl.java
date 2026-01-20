package com.outpatientmanagement.serviceimplementation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.outpatientmanagement.dto.request.AppointmentRequest;
import com.outpatientmanagement.dto.response.AppointmentResponse;
import com.outpatientmanagement.entity.Appointment;
import com.outpatientmanagement.entity.Doctor;
import com.outpatientmanagement.entity.Patient;
import com.outpatientmanagement.entity.User;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.mapper.AppointmentMapper;
import com.outpatientmanagement.repository.AppointmentRepository;
import com.outpatientmanagement.repository.DoctorRepository;
import com.outpatientmanagement.repository.PatientRepository;
import com.outpatientmanagement.repository.UserRepository;
import com.outpatientmanagement.service.AppointmentService;

@Service
public class AppointmentServiceImpl implements AppointmentService {
 
    private static final Logger logger =
            LoggerFactory.getLogger(AppointmentServiceImpl.class);
 
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentMapper appointmentMapper;
 
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, UserRepository userRepository,
			PatientRepository patientRepository, DoctorRepository doctorRepository,
			AppointmentMapper appointmentMapper) {
		super();
		this.appointmentRepository = appointmentRepository;
		this.userRepository = userRepository;
		this.patientRepository = patientRepository;
		this.doctorRepository = doctorRepository;
		this.appointmentMapper = appointmentMapper;
	}

	@Override
    public ResponseEntity<?> createAppointment(AppointmentRequest request) {
     
        logger.info("Creating appointment");
     
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
     
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("User not authenticated");
        }
     
        String username = authentication.getName();
     
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
     
        Patient patient = patientRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
     
        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new ModelNotFoundException("Doctor not found"));
     
        Appointment appointment =
                appointmentMapper.toRequest(request, patient, doctor);
     
        LocalDateTime appointmentDateTime =
                LocalDateTime.of(
                    request.getAppointmentDate(),
                    request.getAppointmentTime()
                );
         
//        String status;
//         
//        if (!doctor.getAvailable()) {
//            status = "PENDING";
//        } else if (appointmentDateTime.isAfter(LocalDateTime.now())) {
//            status = "BOOKED";
//        } else {
//            status = "COMPLETED";
//        }
//        
        appointment.setPatient(patient);
        appointment.setStatus("PENDING"); 
     
        Appointment saved =
                appointmentRepository.save(appointment);
     
        return ResponseEntity.ok(
                appointmentMapper.toResponse(saved)
        );
    }
     
 
    @Override
    public ResponseEntity<?> getAppointmentById(Long id) {
 
        logger.info("Fetching appointment by id: ", id);
 
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ModelNotFoundException("Appointment not found"));
 
        return ResponseEntity.ok(
                appointmentMapper.toResponse(appointment));
    }
 
    @Override
    public ResponseEntity<?> getAllAppointments() {
 
        logger.info("Fetching all appointments");
 
        return ResponseEntity.ok(
                appointmentRepository.findAll()
                        .stream()
                        .map(appointmentMapper::toResponse)
                        .toList());
    }
 
    @Override
    public ResponseEntity<?> updateAppointment(
            Long id, AppointmentRequest request) {
 
        logger.info("Updating appointment id: ", id);
 
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ModelNotFoundException("Appointment not found"));
 
        appointment.setAppointmentDate(
                request.getAppointmentDate());
        appointment.setAppointmentTime(
                request.getAppointmentTime());
        Appointment updated =
                appointmentRepository.save(appointment);
 
        return ResponseEntity.ok(
                appointmentMapper.toResponse(updated));
    }
 
    @Override
    public ResponseEntity<?> deleteAppointment(Long id) {
 
        logger.info("Deleting appointment id: ", id);
 
        appointmentRepository.deleteById(id);
        return ResponseEntity.ok("Appointment deleted");
    }
 
    @Override
    public ResponseEntity<?> getAppointmentsByPatientId(Long patientId) {
 
        logger.info("Fetching appointments for patientId: ", patientId);
 
        return ResponseEntity.ok(
                appointmentRepository.findByPatient_PatientId(patientId)
                        .stream()
                        .map(appointmentMapper::toResponse)
                        .toList());
    }
 
    @Override
    public ResponseEntity<?> getAppointmentsByDoctorId(Long doctorId) {
 
        logger.info("Fetching appointments for doctorId: {}", doctorId);
 
        List<AppointmentResponse> responseList =
                appointmentRepository.findByDoctor_DoctorId(doctorId)
                        .stream()
                        .map(appointmentMapper::toResponse)
                        .collect(Collectors.toList());
 
        return ResponseEntity.ok(responseList);
    }
 
    @Override
    public ResponseEntity<?> getAppointmentsByStatus(String status) {
 
        logger.info("Fetching appointments with status: ", status);
 
        return ResponseEntity.ok(
                appointmentRepository.findByStatus(status)
                        .stream()
                        .map(appointmentMapper::toResponse)
                        .toList());
    }
 
    @Override
    public ResponseEntity<?> cancelAppointment(Long id) {
 
        logger.info("Cancelling appointment id: {}", id);
 
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() ->
                        new ModelNotFoundException("Appointment not found"));
 
        appointment.setStatus("CANCELLED");
        appointmentRepository.save(appointment);
 
        return ResponseEntity.ok("Appointment cancelled");
    }
}
 
