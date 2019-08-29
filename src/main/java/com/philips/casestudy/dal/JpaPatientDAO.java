package com.philips.casestudy.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class JpaPatientDAO implements PatientDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Patient save(Patient patient, int bedId) {
        Bed bed = em.find(Bed.class, bedId);
        patient.setBed(bed);
        em.persist(patient);
        return patient;
    }

  /*  @Override
    public List<Patient> findAll() { // this is different as we want to display all patients in ICU
        Query q = em.createQuery("select p from Patient p");
        return q.getResultList();
    }*/

    @Override
    public Patient findById(int id) {
        return em.find(Patient.class, id);
    }

    @Override
	public void deletebyId(int id) {
        /* before deleting or discharging a patient we need to update the occupancy status of its associated bed"*/

        /*

        Bed bed = em.createQuery("select bed_id from Patient p where p.id = :paramId").setParameter("paramId", id).getSingleResult();
        bed.setPatient(null);
        bed.setOccupancystatus();
        */

        Query q = em.createQuery("select p from Patient p where p.id = :paramId").setParameter("paramId", id);
        List<Patient> patientList = q.getResultList(); // To Do -> try to do this by JPA query nesting
        Patient patient = patientList.get(0);
        Bed bed = patient.getBed();

        // int bedId = em.createQuery("select bed_id from Patient p where p.id = :paramId").setParameter("paramId", id).getFirstResult(); // To Check the getFirstResult() works correctly !!!!!!!!!!!1
        // Bed bed = em.find(Bed.class, bedId);
        bed.setPatient(null);
        bed.setisAvailable();

        em.createQuery("delete p from Patient p where p.id = :paramId").setParameter("paramId", id).executeUpdate();
	}

}