package com.philips.casestudy.web;

import java.util.List;

import com.philips.casestudy.domain.MonitoringVitals;
import com.philips.casestudy.service.PatientService;
import com.philips.casestudy.service.VitalServiceRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VitalController{

    @Autowired
    VitalServiceRandom vitalService;
    
    @Autowired
    PatientService patientService;

    @RequestMapping(value = "/api/monitor/{id}", method = RequestMethod.GET)
    public List<MonitoringVitals> getAllStations(@PathVariable("id") int patientId){
        
        // Patient patient = patientService.getPatient(patientId);
        
        return vitalService.initialiseVitals();
    }

    
}