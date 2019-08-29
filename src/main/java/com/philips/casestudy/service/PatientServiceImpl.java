package com.philips.casestudy.service;

import com.philips.casestudy.dal.BedDAO;
import com.philips.casestudy.dal.PatientDAO;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;

import org.dom4j.IllegalAddException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientDAO patientdao;
    @Autowired
    BedDAO beddao;

    @Override
    public int addNewPatient(Patient patient, int bedId) { // add a patient to this bed only if the bed is free
        Bed bed = beddao.findById(bedId); 
        if(bed.getisAvailable()){ // bed is free
           Patient savedPatient = patientdao.save(patient, bedId);
           return savedPatient.getId();
        }
        else {
            throw new IllegalAddException("The bed is not empty to be assigned!");
        }
    }

   /* @Override
    public List<Patient> getAllPatients() {
        return null;
    }*/

    @Override
    public Patient getPatient(int patientId) {
        return patientdao.findById(patientId);
    }

	@Override
	public void dischargePatient(int patientId) {
		patientdao.deletebyId(patientId);
	}

}