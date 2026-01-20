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

import com.outpatientmanagement.entity.Prescription;
import com.outpatientmanagement.exception.ModelNotFoundException;
import com.outpatientmanagement.repository.PrescriptionRepository;
import com.outpatientmanagement.serviceimplementation.PrescriptionServiceImpl;
 
@ExtendWith(MockitoExtension.class)
class PrescriptionServiceImplTest {
 
    @Mock
    private PrescriptionRepository prescriptionRepository;
 
    @InjectMocks
    private PrescriptionServiceImpl prescriptionService;
 
    private Prescription prescription;
 
    @BeforeEach
    void setUp() {
        prescription = new Prescription();
    }
 
    @Test
    void getAllPrescriptionsSuccess() {
        when(prescriptionRepository.findAll())
                .thenReturn(List.of(prescription));
 
        ResponseEntity<?> response = prescriptionService.getAllPrescriptions();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
 
    @Test
    void getPrescriptionByIdSuccess() {
        when(prescriptionRepository.findById(any()))
                .thenReturn(Optional.of(prescription));
 
        ResponseEntity<?> response = prescriptionService.getPrescriptionById(1L);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
 
    @Test
    void deletePrescriptionSuccess() {
        when(prescriptionRepository.existsById(any()))
                .thenReturn(true);
 
        ResponseEntity<?> response = prescriptionService.deletePrescription(1L);
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(prescriptionRepository).deleteById(1L);
    }
 
    @Test
    void getPrescriptionByIdFailure() {
        when(prescriptionRepository.findById(any()))
                .thenReturn(Optional.empty());
 
        assertThrows(ModelNotFoundException.class,
                () -> prescriptionService.getPrescriptionById(1L));
    }
 
    @Test
    void deletePrescriptionFailure() {
        when(prescriptionRepository.existsById(any()))
                .thenReturn(false);
 
        assertThrows(ModelNotFoundException.class,
                () -> prescriptionService.deletePrescription(1L));
    }
 
    @Test
    void getAllPrescriptionsEmptyFailure() {
        when(prescriptionRepository.findAll())
                .thenReturn(Collections.emptyList());
 
        ResponseEntity<?> response = prescriptionService.getAllPrescriptions();
 
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
}
 