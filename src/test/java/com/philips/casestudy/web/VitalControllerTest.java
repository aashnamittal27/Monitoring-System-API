package com.philips.casestudy.web;

import static org.junit.Assert.assertTrue;

import java.util.List;

import com.philips.casestudy.domain.Patient;
import com.philips.casestudy.service.MonitoringVitals;
import com.philips.casestudy.service.PatientService;
import com.philips.casestudy.utils.Data;

import org.junit.Test;
import org.mockito.Mockito;

public class VitalControllerTest {

    @Test
    public void monitor2() {
        PatientService ps = Mockito.mock(PatientService.class);
        Patient p = new Patient();
        Mockito.when(ps.getPatient(1)).thenReturn(p);
        VitalController vc = new VitalController();
        vc.setPatientService(ps);

        Data data = new Data();
        data.setPatientId(1);
        data.setPulseRate(70);
        data.setSpo2(90);
        data.setTemperature(30);

        List<MonitoringVitals> mv = vc.monitor(data);
        assertTrue(mv.size() > 0);
    }
}