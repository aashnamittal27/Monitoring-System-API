package com.philips.casestudy.web;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.service.PatientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController{

    @Autowired
    PatientService patientService;

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patient", method = RequestMethod.POST)
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {

        Bed b = patient.getBed();
        int bedId = b.getBedId();
        
        Patient p = patientService.addNewPatient(patient, bedId);

        if (p == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/patient/" + p.getId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/patient", method = RequestMethod.GET)
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        if (patients == null) {
            return new ResponseEntity<>(new ArrayList<Patient>(), HttpStatus.OK);
        }

        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @RequestMapping(value = "/patient/{pid}", method = RequestMethod.GET)
    public ResponseEntity<Patient> getPatientById(@PathVariable("pid") int id){
        
        Patient patient = patientService.getPatient(id);

        if (patient != null) {
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/patient/{pid}", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> dischargePatient(@PathVariable("pid") int id){

        Patient patient = patientService.getPatient(id);
        if (patient != null) {
            boolean flag = patientService.dischargePatient(id);
            if (flag)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}