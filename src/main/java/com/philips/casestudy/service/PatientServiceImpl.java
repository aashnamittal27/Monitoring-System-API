package com.philips.casestudy.service;

import java.util.List;

import com.philips.casestudy.dal.BedDAO;
import com.philips.casestudy.dal.PatientDAO;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientDAO patientdao;

    @Autowired
    BedDAO beddao;

    public void setPatientdao(PatientDAO patientdao) {
        this.patientdao = patientdao;
    }

    public void setBeddao(BedDAO beddao) {
        this.beddao = beddao;
    }

    @Override
    public Patient addNewPatient(Patient patient, int bedId) { // add a patient to this bed only if the bed is free
        Bed bed = beddao.findBed(bedId); 
        if (bed == null) return null;

        if (bed.getisAvailable()){ // bed is free
           Patient savedPatient = patientdao.addPatient(patient, bedId);
           return savedPatient;
        } else {
            return null;
        }
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientdao.findAll();
    }

    @Override
    public Patient getPatient(int patientId) {
        return patientdao.findPatient(patientId);
    }

	@Override
	public boolean dischargePatient(int patientId) {
        Patient patient = patientdao.findPatient(patientId);
        if (patient == null) return false;

        Bed bed = patient.getBed();
        bed.setisAvailable(true);
        beddao.update(bed);

        patientdao.deletePatient(patientId);
        return true;
	}
}