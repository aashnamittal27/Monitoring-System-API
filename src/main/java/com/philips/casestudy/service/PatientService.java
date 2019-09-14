package com.philips.casestudy.service;

import java.util.List;

import com.philips.casestudy.domain.Patient;

public interface PatientService{
    int addNewPatient(Patient patient, int bedId);
    List<Patient> getAllPatients();
    Patient getPatient(int patientId);
    void dischargePatient(int patientId);
}