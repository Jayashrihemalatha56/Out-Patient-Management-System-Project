package com.outpatientmanagement.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.outpatientmanagement.dto.response.PatientResponse;
import com.outpatientmanagement.entity.Patient;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.mapper.PatientMapper;
import com.outpatientmanagement.repository.PatientRepository;
import com.outpatientmanagement.serviceimplementation.PatientServiceImpl;
 
@ExtendWith(MockitoExtension.class)
class PatientServiceImplTest {
 
    @Mock
    private PatientRepository patientRepository;
 
    @Mock
    private PatientMapper patientMapper;
 
    @InjectMocks
    private PatientServiceImpl patientService;
 
    private Patient patient;
    private PatientResponse patientResponse;
 
    @BeforeEach
    void setup() {
        patient = new Patient();
        patientResponse = new PatientResponse();
    }
 
    @Test
    void getPatientByIdSuccess() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientMapper.toResponse(patient)).thenReturn(patientResponse);
 
        ResponseEntity<?> response = patientService.getPatientById(1L);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
 
    @Test
    void getAllPatientsSuccess() {
        when(patientRepository.findAll()).thenReturn(Arrays.asList(patient));
        when(patientMapper.toResponse(patient)).thenReturn(patientResponse);
 
        ResponseEntity<?> response = patientService.getAllPatients();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
 
 
    @Test
    void getPatientByIdFailure() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
 
        assertThrows(ModelNotFoundException.class,
                () -> patientService.getPatientById(1L));
    }
 
    @Test
    void getAllPatientsEmptyFailure() {
        when(patientRepository.findAll()).thenReturn(Collections.emptyList());
 
        ResponseEntity<?> response = patientService.getAllPatients();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
 