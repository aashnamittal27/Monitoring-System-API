package com.philips.casestudy.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;

import org.junit.Test;
import org.mockito.Mockito;

public class JpaPatientDAOTest {
    
    @Test
    public void addPatientTest() {
        Bed bed = new Bed();
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(em.find(Bed.class, 1)).thenReturn(bed);
        JpaPatientDAO dao = new JpaPatientDAO();
        dao.setEntityManager(em);
        Patient patient = new Patient();
        assertEquals(patient, dao.addPatient(patient, 1));
        assertEquals(bed, patient.getBed());
        assertFalse(bed.getisAvailable());
    }

    @Test
    public void findAllTest() {
        List<Object> patients = List.of(new Patient(), new Patient());
        Query query = Mockito.mock(Query.class);
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(patients);
        JpaPatientDAO dao = new JpaPatientDAO();
        dao.setEntityManager(em);
        assertTrue(patients.containsAll(dao.findAll()));
    }

    @Test
    public void findPatientTest() {
        Patient patient = new Patient();
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(em.find(Patient.class, 1)).thenReturn(patient);
        JpaPatientDAO dao = new JpaPatientDAO();
        dao.setEntityManager(em);
        assertEquals(patient, dao.findPatient(1));
    }

    @Test
    public void deletePatientTest() {
        Query query = Mockito.mock(Query.class);
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.anyInt())).thenReturn(query);
        JpaPatientDAO dao = new JpaPatientDAO();
        dao.setEntityManager(em);
        dao.deletePatient(1);
    }
}