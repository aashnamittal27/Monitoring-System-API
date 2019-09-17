package com.philips.casestudy.dal;

import java.util.List;

import com.philips.casestudy.domain.Patient;

public interface PatientDAO{
    Patient addPatient(Patient patient, int bedId);
    List<Patient> findAll();
    Patient findPatient(int id);
    void deletePatient(int id);
}