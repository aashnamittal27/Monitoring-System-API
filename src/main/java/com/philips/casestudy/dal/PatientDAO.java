package com.philips.casestudy.dal;

import com.philips.casestudy.domain.Patient;

public interface PatientDAO{
    Patient save(Patient patient,int bedId);
    //List<Patient> findAll();
    Patient findById(int id);
    void deletebyId(int id);
}