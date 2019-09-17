package com.philips.casestudy.service;

import static org.junit.Assert.assertEquals;
import com.philips.casestudy.service.MonitoringVitals;
import com.philips.casestudy.service.PulseRate;
import org.junit.Test;

public class PulseRateTest {
    @Test
    public void prMoreThan254() {
        PulseRate test = new PulseRate(255);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(6), test.getResult());
    }

    @Test
    public void pr254() {
        PulseRate test = new PulseRate(254);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(3), test.getResult());
    }

    @Test
    public void prLessThan254() {
        PulseRate test = new PulseRate(253);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(3), test.getResult());
    }

    @Test
    public void prMoreThan220() {
        PulseRate test = new PulseRate(221);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(3), test.getResult());
    }

    @Test
    public void pr220() {
        PulseRate test = new PulseRate(220);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }

    @Test
    public void prLessThan220() {
        PulseRate test = new PulseRate(219);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());

    }

    @Test
    public void prMoreThan100() {
        PulseRate test = new PulseRate(101);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }

    @Test
    public void pr100() {
        PulseRate test = new PulseRate(100);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }

    @Test
    public void prLessThan100() {
        PulseRate test = new PulseRate(99);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());

    }

    @Test
    public void prMoreThan60() {
        PulseRate test = new PulseRate(61);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }


    @Test
    public void pr60() {
        PulseRate test = new PulseRate(60);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }


    @Test
    public void prLessThan60() {
        PulseRate test = new PulseRate(59);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }


    @Test
    public void prMoreThan40() {
        PulseRate test = new PulseRate(41);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }

    @Test
    public void pr40() {
        PulseRate test = new PulseRate(40);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }


    @Test
    public void prLessThan40() {
        PulseRate test = new PulseRate(39);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
    }

    @Test
    public void prMoreThan30() {
        PulseRate test = new PulseRate(31);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
    }

    @Test
    public void pr30() {
        PulseRate test = new PulseRate(30);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
    }

    @Test
    public void prLessThan30() {
        PulseRate test = new PulseRate(29);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(0), test.getResult());
    }
}
  