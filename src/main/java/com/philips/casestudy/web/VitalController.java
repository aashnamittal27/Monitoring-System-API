package com.philips.casestudy.web;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy.service.MonitoringVitals;
import com.philips.casestudy.service.PatientService;
import com.philips.casestudy.service.PulseRate;
import com.philips.casestudy.service.Spo2;
import com.philips.casestudy.service.Temperature;
import com.philips.casestudy.utils.Data;

@RestController
public class VitalController {

  @Autowired
  PatientService patientService;

  public void setPatientService(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping(value = "/monitor")
  public List<MonitoringVitals> monitor(@RequestBody Data data) {
    final MonitoringVitals pulseRate = new PulseRate(data.getPulseRate());
    final MonitoringVitals spo2 = new Spo2(data.getSpo2());
    final MonitoringVitals temperature = new Temperature(data.getTemperature());

    final List<MonitoringVitals> vitals = new ArrayList<MonitoringVitals>() {
      private static final long serialVersionUID = 1L;

      {
        add(pulseRate);
        add(spo2);
        add(temperature);
      }
    };

    final List<MonitoringVitals> alertList = new ArrayList<>();

    for (final MonitoringVitals vital : vitals) {
      vital.vitalChecker();
      alertList.add(vital);
    }
    return alertList;
  }
}