package com.philips.casestudy.web;

import java.util.ArrayList;
import java.util.List;

//import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.service.MonitoringVitals;
import com.philips.casestudy.service.PatientService;
import com.philips.casestudy.service.PulseRate;
import com.philips.casestudy.service.Spo2;
import com.philips.casestudy.service.Temperature;
import com.philips.casestudy.utils.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VitalController {

    @Autowired
    PatientService patientService;

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public List<MonitoringVitals> monitor(@RequestBody Data data) {
        MonitoringVitals pulseRate = new PulseRate(data.getPulseRate());
        MonitoringVitals spo2 = new Spo2(data.getSpo2());
        MonitoringVitals temperature = new Temperature(data.getTemperature());

        List<MonitoringVitals> vitals = new ArrayList<MonitoringVitals>() {
            private static final long serialVersionUID = 1L;

            {
                add(pulseRate);
                add(spo2);
                add(temperature);
            }
        };

        List<MonitoringVitals> alertList = new ArrayList<>();
        
        for (MonitoringVitals vital : vitals) {
            vital.vitalChecker();
            alertList.add(vital);
            System.out.println(vital);
        }
        return alertList;
    }
}