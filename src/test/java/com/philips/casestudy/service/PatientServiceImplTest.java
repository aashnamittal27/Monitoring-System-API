package com.philips.casestudy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.philips.casestudy.dal.BedDAO;
import com.philips.casestudy.dal.PatientDAO;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;

import org.junit.Test;
import org.mockito.Mockito;

public class PatientServiceImplTest {
    
    @Test
    public void addNewPatientTest() {
        BedDAO bedDao = Mockito.mock(BedDAO.class);
        PatientDAO patientDao = Mockito.mock(PatientDAO.class);

        Mockito.when(bedDao.findBed(1)).thenReturn(null);

        PatientServiceImpl psi = new PatientServiceImpl();
        psi.setBeddao(bedDao);
        psi.setPatientdao(patientDao);

        Patient patient = new Patient();
        assertNull(psi.addNewPatient(patient, 1));
    }

    @Test
    public void addNewPatientTest2() {
        BedDAO bedDao = Mockito.mock(BedDAO.class);
        PatientDAO patientDao = Mockito.mock(PatientDAO.class);

        Mockito.when(bedDao.findBed(1)).thenReturn(new Bed(false));

        PatientServiceImpl psi = new PatientServiceImpl();
        psi.setBeddao(bedDao);
        psi.setPatientdao(patientDao);

        Patient patient = new Patient();
        assertNull(psi.addNewPatient(patient, 1));
    }

    @Test
    public void addNewPatientTest3() {
        BedDAO bedDao = Mockito.mock(BedDAO.class);
        PatientDAO patientDao = Mockito.mock(PatientDAO.class);
        Patient patient = new Patient();

        Mockito.when(bedDao.findBed(1)).thenReturn(new Bed(true));
        Mockito.when(patientDao.addPatient(patient, 1)).thenReturn(patient);

        PatientServiceImpl psi = new PatientServiceImpl();
        psi.setBeddao(bedDao);
        psi.setPatientdao(patientDao);

        assertEquals(patient, psi.addNewPatient(patient, 1));
    }

    @Test
    public void getAllPatientsTest() {
        PatientDAO patientDao = Mockito.mock(PatientDAO.class);
        List<Patient> patients = List.of(new Patient());

        Mockito.when(patientDao.findAll()).thenReturn(patients);

        PatientServiceImpl psi = new PatientServiceImpl();
        psi.setPatientdao(patientDao);

        assertTrue(patients.containsAll(psi.getAllPatients()));
    }

    @Test
    public void getPatientTest() {
        PatientDAO patientDao = Mockito.mock(PatientDAO.class);
        Patient patient = new Patient();

        Mockito.when(patientDao.findPatient(1)).thenReturn(patient);

        PatientServiceImpl psi = new PatientServiceImpl();
        psi.setPatientdao(patientDao);

        assertEquals(patient, psi.getPatient(1));
    }

    @Test
    public void dischargePatient1() {
        PatientDAO patientDao = Mockito.mock(PatientDAO.class);
        BedDAO bedDao = Mockito.mock(BedDAO.class);

        Mockito.when(patientDao.findPatient(1)).thenReturn(null);

        PatientServiceImpl psi = new PatientServiceImpl();
        psi.setPatientdao(patientDao);
        psi.setBeddao(bedDao);

        assertFalse(psi.dischargePatient(1));
    }

    @Test
    public void dischargePatient2() {
        PatientDAO patientDao = Mockito.mock(PatientDAO.class);
        BedDAO bedDao = Mockito.mock(BedDAO.class);

        Patient patient = new Patient();
        Bed bed = new Bed(false);
        patient.setBed(bed);

        Mockito.when(patientDao.findPatient(1)).thenReturn(patient);

        PatientServiceImpl psi = new PatientServiceImpl();
        psi.setPatientdao(patientDao);
        psi.setBeddao(bedDao);

        assertTrue(psi.dischargePatient(1));
    }
}