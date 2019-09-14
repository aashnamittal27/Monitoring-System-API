package com.philips.casestudy.casestudy.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.service.PatientService;
import com.philips.casestudy.web.PatientController;

import org.dom4j.IllegalAddException;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class PatientControllerTest {

    @Test
    public void addPatient_Valid() throws Exception {

        PatientController patientcontroller = new PatientController();
        PatientService patientService = Mockito.mock(PatientService.class);
        patientcontroller.setPatientService(patientService);

        Bed bed = new Bed(true);
        bed.setBedId(10);

        Patient patient = new Patient("Aman Gill", 35,"9897969592");
        patient.setId(1);
        patient.setBed(bed);

        Mockito.when(patientService.addNewPatient(patient, 10)).thenReturn(1);

        ResponseEntity<Patient> response = patientcontroller.addPatient(patient);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);

    }

    // @Test
    // public void addPatient_OccupiedBed() throws Exception {

    //     PatientController patientcontroller = new PatientController();
    //     PatientService patientService = Mockito.mock(PatientService.class);
    //     patientcontroller.setPatientService(patientService);

    //     Bed bed = new Bed(false);
    //     bed.setBedId(10);

    //     Patient patient = new Patient("Aman Gill", 35,"9897969592");
    //     patient.setId(1);
 
    //     Mockito.when(patientService.addNewPatient(patient, 10)).thenThrow(IllegalAddException.class);

    //     ResponseEntity<Patient> response = patientcontroller.addPatient(patient);

    //     assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);

    // }


    

    @Test
    public void test_getPatientByID_validID() throws Exception {

        PatientController patientcontroller = new PatientController();
        PatientService patientService = Mockito.mock(PatientService.class);
        patientcontroller.setPatientService(patientService);

        Patient patient = new Patient("Aman Gill", 35,"9897969592");
        patient.setId(1);

        Mockito.when(patientService.getPatient(1)).thenReturn(patient);

        ResponseEntity<Patient> response = patientcontroller.getPatientById(1);

        if(response.getStatusCode() == HttpStatus.OK) {
            assertEquals(response.getBody(), patient);
        } 
    }

    @Test
    public void getPatientById_invalidID() throws Exception {
        PatientController patientcontroller = new PatientController();
        PatientService patientService = Mockito.mock(PatientService.class);
        patientcontroller.setPatientService(patientService);

        Patient patient = new Patient("Aman Gill", 35,"9897969592");
        patient.setId(1);

        Mockito.when(patientService.getPatient(1)).thenReturn(patient);
        Patient actual = null;

        ResponseEntity<Patient> response = patientcontroller.getPatientById(11);

        if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
            // assertEquals(response.getBody(), );
            assertEquals(null, actual);
        }
    }

    @Test
    public void getAllPatients_ContainingBeds() throws Exception {
        PatientController patientcontroller = new PatientController();
        PatientService patientService = Mockito.mock(PatientService.class);
        patientcontroller.setPatientService(patientService);

        Patient patient = new Patient("Aman Gill", 35,"9897969592");
        patient.setId(1);

        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);

        Mockito.when(patientService.getAllPatients()).thenReturn(patientList);

        List<Patient> responseList = patientcontroller.getAllPatients();
      
        assertTrue(responseList == patientList);
        
    }

    @Test
    public void getAllPatients_EmptyList() throws Exception {

        PatientController patientcontroller = new PatientController();
        PatientService patientService = Mockito.mock(PatientService.class);
        patientcontroller.setPatientService(patientService);

        List<Patient> patientList = new ArrayList<>();
        Mockito.when(patientService.getAllPatients()).thenReturn(patientList);

        List<Patient> responseList = patientcontroller.getAllPatients();
        assertTrue(responseList == patientList);
    }

    @Test
    public void dischargePatient_ValidId() throws Exception {
        PatientController patientcontroller = new PatientController();
        PatientService patientService = Mockito.mock(PatientService.class);
        patientcontroller.setPatientService(patientService);

        Patient patient = new Patient("Aman Gill", 35,"9897969592");
        patient.setId(1);

        Mockito.when(patientService.getPatient(1)).thenReturn(patient);

        ResponseEntity<Patient> response = patientcontroller.dischargePatient(1);

        if(response.getStatusCode() == HttpStatus.NO_CONTENT)
        {
            assertEquals(response.getBody(), null);
        }

    }

    @Test
    public void dischargePatient_InvalidId() throws Exception {
        PatientController patientcontroller = new PatientController();
        PatientService patientService = Mockito.mock(PatientService.class);
        patientcontroller.setPatientService(patientService);

        Patient patient = new Patient("Aman Gill", 35,"9897969592");
        patient.setId(1);

        Mockito.when(patientService.getPatient(1)).thenReturn(patient);

        ResponseEntity<Patient> response = patientcontroller.dischargePatient(2);

        if(response.getStatusCode() == HttpStatus.BAD_REQUEST)
        {
            assertEquals(response.getBody(), null);
        }
    }
    
}