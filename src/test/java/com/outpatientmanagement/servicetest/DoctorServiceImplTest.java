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

import com.outpatientmanagement.entity.Doctor;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.repository.DoctorRepository;
import com.outpatientmanagement.serviceimplementation.DoctorServiceImpl;

 
@ExtendWith(MockitoExtension.class)
class DoctorServiceImplTest {
 
    @Mock
    private DoctorRepository doctorRepository;
 
    @InjectMocks
    private DoctorServiceImpl doctorService;
 
    private Doctor doctor;
 
    @BeforeEach
    void setUp() {
        doctor = new Doctor();
    }
 
    @Test
    void getAllDoctorsSuccess() {
        when(doctorRepository.findAll())
                .thenReturn(List.of(doctor));
 
        ResponseEntity<?> response = doctorService.getAllDoctors();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
 
    @Test
    void getDoctorByIdSuccess() {
        when(doctorRepository.findById(any()))
                .thenReturn(Optional.of(doctor));
 
        ResponseEntity<?> response = doctorService.getDoctorById(1L);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
 
    @Test
    void deleteDoctorSuccess() {
        when(doctorRepository.existsById(any()))
                .thenReturn(true);
 
        ResponseEntity<?> response = doctorService.deleteDoctor(1L);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(doctorRepository).deleteById(1L);
    }
 
    @Test
    void getDoctorByIdFailure() {
        when(doctorRepository.findById(any()))
                .thenReturn(Optional.empty());
 
        assertThrows(ModelNotFoundException.class,
                () -> doctorService.getDoctorById(1L));
    }
 
    @Test
    void deleteDoctorFailure() {
        when(doctorRepository.existsById(any()))
                .thenReturn(false);
 
        assertThrows(ModelNotFoundException.class,
                () -> doctorService.deleteDoctor(1L));
    }
 
    @Test
    void getAllDoctorsEmptyFailure() {
        when(doctorRepository.findAll())
                .thenReturn(Collections.emptyList());
 
        ResponseEntity<?> response = doctorService.getAllDoctors();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
 