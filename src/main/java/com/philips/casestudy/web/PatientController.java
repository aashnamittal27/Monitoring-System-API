package com.philips.casestudy.web;

import java.net.URI;

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

public class PatientController{

    @Autowired
    PatientService patientService;

    @RequestMapping(value = "/api/icu/{id}/beds/{bid}/patients", method = RequestMethod.POST)
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient,@PathVariable("bid") int bedId){

        try{
            int id = patientService.addNewPatient(patient,bedId);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/api/icu/{id}/bed/{bid}/patient"+id));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

  /*  @RequestMapping(value = "/api/icu/{id}/beds/{bid}/patients", method = RequestMethod.GET)
    public List<Patient> getAllStations(){
        return stationService.getAllStations();
    }*/

    @RequestMapping(value = "/api/icu/{id}/beds/{bid}/patients/{pid}", method = RequestMethod.GET)
    public ResponseEntity<Patient> getPatientById(@PathVariable("pid")int id){
        
        Patient patient = patientService.getPatient(id);

        if(patient!=null){
            return new ResponseEntity<>(patient, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/api/icu/{id}/beds/{bid}/patients/{pid}", method = RequestMethod.DELETE)
    public ResponseEntity<Patient> dischargePatient(@PathVariable("pid") int id){

        Patient station = patientService.getPatient(id);
        if(station!=null){
            patientService.dischargePatient(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}