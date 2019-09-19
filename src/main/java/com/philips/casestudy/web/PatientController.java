package com.philips.casestudy.web;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.service.PatientService;

@RestController
public class PatientController{

  @Autowired
  PatientService patientService;

  public void setPatientService(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping(value = "/patient")
  public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {

    final Bed b = patient.getBed();
    final int bedId = b.getBedId();

    final Patient p = patientService.addNewPatient(patient, bedId);

    if (p == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    final HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create("/patient/" + p.getId()));
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @GetMapping(value = "/patient")
  public ResponseEntity<List<Patient>> getAllPatients() {
    final List<Patient> patients = patientService.getAllPatients();
    if (patients == null) {
      return new ResponseEntity<>(new ArrayList<Patient>(), HttpStatus.OK);
    }

    return new ResponseEntity<>(patients, HttpStatus.OK);
  }

  @GetMapping(value = "/patient/{pid}")
  public ResponseEntity<Patient> getPatientById(@PathVariable("pid") int id){

    final Patient patient = patientService.getPatient(id);

    if (patient != null) {
      return new ResponseEntity<>(patient, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value = "/patient/{pid}")
  public ResponseEntity<Patient> dischargePatient(@PathVariable("pid") int id){

    final Patient patient = patientService.getPatient(id);
    if (patient != null) {
      final boolean flag = patientService.dischargePatient(id);
      if(flag) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}