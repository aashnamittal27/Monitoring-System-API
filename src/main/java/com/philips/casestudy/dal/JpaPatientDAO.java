package com.philips.casestudy.dal;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.utils.GenericUtils;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class JpaPatientDAO implements PatientDAO {

    @PersistenceContext
    EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Patient addPatient(Patient patient, int bedId) {
        Bed bed = em.find(Bed.class, bedId);
        patient.setBed(bed);
        em.persist(patient);
        return patient;
    }

    public List<Patient> findAll() { // this is different as we want to display all patients in ICU
        return GenericUtils.castList(Patient.class, em.createQuery("select p from Patient p").getResultList());
    }

    @Override
    public Patient findPatient(int id) {
        return em.find(Patient.class, id);
    }

    @Override
	public void deletePatient(int id) {
        em.createQuery("delete from Patient p where p.id = :paramId").setParameter("paramId", id).executeUpdate();
	}
}