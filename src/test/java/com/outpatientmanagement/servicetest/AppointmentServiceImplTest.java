package com.outpatientmanagement.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.outpatientmanagement.entity.Appointment;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.repository.AppointmentRepository;
import com.outpatientmanagement.serviceimplementation.AppointmentServiceImpl;


@ExtendWith(MockitoExtension.class)
class AppointmentServiceImplTest {
 
    @Mock
    private AppointmentRepository appointmentRepository;
 
    @InjectMocks
    private AppointmentServiceImpl appointmentService;
 
    private Appointment appointment;
 
    @BeforeEach
    void setUp() {
        appointment = new Appointment();
    }
 
    @Test
    void getAllAppointmentsSuccess() {
        when(appointmentRepository.findAll())
                .thenReturn(List.of(appointment));
 
        ResponseEntity<?> response = appointmentService.getAllAppointments();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
 
    @Test
    void getAppointmentByIdSuccess() {
        when(appointmentRepository.findById(any()))
                .thenReturn(Optional.of(appointment));
 
        ResponseEntity<?> response = appointmentService.getAppointmentById(1L);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
 
    @Test
    void deleteAppointmentSuccess() {
        when(appointmentRepository.existsById(any()))
                .thenReturn(true);
 
        ResponseEntity<?> response = appointmentService.deleteAppointment(1L);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(appointmentRepository).deleteById(1L);
    }
 
    @Test
    void getAppointmentByIdFailure() {
        when(appointmentRepository.findById(any()))
                .thenReturn(Optional.empty());
 
        assertThrows(ModelNotFoundException.class,
                () -> appointmentService.getAppointmentById(1L));
    }
 
    @Test
    void deleteAppointmentFailure() {
        when(appointmentRepository.existsById(any()))
                .thenReturn(false);
 
        assertThrows(ModelNotFoundException.class,
                () -> appointmentService.deleteAppointment(1L));
    }
 
    @Test
    void getAllAppointmentsEmptyFailure() {
        when(appointmentRepository.findAll())
                .thenReturn(Collections.emptyList());
 
        ResponseEntity<?> response = appointmentService.getAllAppointments();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
 