package com.philips.casestudy.web;

import java.net.URI;
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
  
    PatientService patientService;

    @Autowired
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patients", method = RequestMethod.POST)
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){

        Bed b = patient.getBed();
        int bedId = b.getBedId();
        
        try{
            int id = patientService.addNewPatient(patient,bedId);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/patients/"+id));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public List<Patient> getAllPatients(){
        return patientService.getAllPatients();
    }

    @RequestMapping(value = "/patients/{pid}", method = RequestMethod.GET)
    public ResponseEntity<Patient> getPatientById(@PathVariable("pid")int id){
        
        Patient patient = patientService.getPatient(id);

        if(patient!=null){
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/patients/{pid}", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> dischargePatient(@PathVariable("pid") int id){

        Patient patient = patientService.getPatient(id);
        if(patient!=null){
            patientService.dischargePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}