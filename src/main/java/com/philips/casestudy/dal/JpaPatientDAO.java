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
        // get bed obj 
        // set the bed for patient
        // set bed for patient
        // set patient for bed

        // Bed bed = em.find(Bed.class, bedId);
        // patient.setBed(bed);
        // bed.setPatient(patient);
        em.persist(patient);
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        Query q = em.createQuery("select p from Patient p");
        return q.getResultList();
    }

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
        Patient patient = findById(id);
        Bed bed = patient.getBed();

        // int bedId = em.createQuery("select bed_id from Patient p where p.id = :paramId").setParameter("paramId", id).getFirstResult(); // To Check the getFirstResult() works correctly !!!!!!!!!!!1
        // Bed bed = em.find(Bed.class, bedId);
        bed.setPatient(null);
        bed.setisAvailable();

        em.createQuery("delete from Patient p where p.id = :paramId").setParameter("paramId", id).executeUpdate();
	}

}