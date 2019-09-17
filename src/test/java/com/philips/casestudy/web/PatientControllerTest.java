package com.philips.casestudy.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.service.PatientService;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatientControllerTest {

    @Test
    public void addPatientReturnsBadRequestForErrors() {
        Bed bed = new Bed();
        bed.setBedId(1);

        Patient p = new Patient();
        p.setBed(bed);

        PatientService ps = Mockito.mock(PatientService.class);
        Mockito.when(ps.addNewPatient(p, 1)).thenReturn(null);

        PatientController pc = new PatientController();
        pc.setPatientService(ps);

        assertEquals(HttpStatus.BAD_REQUEST, pc.addPatient(p).getStatusCode());
    }

    @Test
    public void addPatientReturnsCreatedAndAURILocation() {
        Bed bed = new Bed();
        bed.setBedId(1);

        Patient p = new Patient();
        p.setBed(bed);
        p.setId(1);

        PatientService ps = Mockito.mock(PatientService.class);
        Mockito.when(ps.addNewPatient(p, 1)).thenReturn(p);

        PatientController pc = new PatientController();
        pc.setPatientService(ps);

        ResponseEntity<Patient> response = pc.addPatient(p);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/patient/1", response.getHeaders().getLocation().toString());
    }

    @Test
    public void getAllPatientsForEmptyPatientRecords() {
        PatientService ps = Mockito.mock(PatientService.class);
        Mockito.when(ps.getAllPatients()).thenReturn(null);
        PatientController pc = new PatientController();
        pc.setPatientService(ps);

        ResponseEntity<List<Patient>> response = pc.getAllPatients();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() == 0);
    }

    @Test
    public void getAllPatientsReturnsListOfPatientsWhenThereArePatients() {
        List<Patient> patients = List.of(new Patient(), new Patient());
        PatientService ps = Mockito.mock(PatientService.class);
        Mockito.when(ps.getAllPatients()).thenReturn(patients);
        PatientController pc = new PatientController();
        pc.setPatientService(ps);

        ResponseEntity<List<Patient>> response = pc.getAllPatients();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().containsAll(patients));
    }

    @Test
    public void getPatientByIdReturnsNotFoundForInvalidId() {
        PatientService ps = Mockito.mock(PatientService.class);
        Mockito.when(ps.getPatient(Mockito.anyInt())).thenReturn(null);
        PatientController pc = new PatientController();
        pc.setPatientService(ps);

        ResponseEntity<Patient> response = pc.getPatientById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getPatientByIdReturnsPatientForValidId() {
        Patient patient = new Patient();
        PatientService ps = Mockito.mock(PatientService.class);
        Mockito.when(ps.getPatient(1)).thenReturn(patient);
        PatientController pc = new PatientController();
        pc.setPatientService(ps);

        ResponseEntity<Patient> response = pc.getPatientById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patient, response.getBody());
    }

    @Test
    public void dischargePatient1() {
        PatientService ps = Mockito.mock(PatientService.class);
        Mockito.when(ps.getPatient(1)).thenReturn(null);
        PatientController pc = new PatientController();
        pc.setPatientService(ps);

        ResponseEntity<Patient> response = pc.dischargePatient(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void dischargePatient2() {
        PatientService ps = Mockito.mock(PatientService.class);
        Patient p = new Patient();
        Mockito.when(ps.getPatient(1)).thenReturn(p);
        Mockito.when(ps.dischargePatient(1)).thenReturn(false);
        PatientController pc = new PatientController();
        pc.setPatientService(ps);

        ResponseEntity<Patient> response = pc.dischargePatient(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void dischargePatient3() {
        PatientService ps = Mockito.mock(PatientService.class);
        Patient p = new Patient();
        Mockito.when(ps.getPatient(1)).thenReturn(p);
        Mockito.when(ps.dischargePatient(1)).thenReturn(true);
        PatientController pc = new PatientController();
        pc.setPatientService(ps);

        ResponseEntity<Patient> response = pc.dischargePatient(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

}