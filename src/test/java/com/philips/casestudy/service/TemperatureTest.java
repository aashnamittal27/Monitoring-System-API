package com.philips.casestudy.service;

import static org.junit.Assert.assertEquals;
import com.philips.casestudy.service.Temperature;
import org.junit.Test;

public class TemperatureTest {
    @Test
    public void tempMoreThan108() {
        Temperature test = new Temperature(108.1);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(6), test.getResult());
    }
  
    
    @Test
    public void temp108() {
        Temperature test = new Temperature(108);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(5), test.getResult());
    }

    @Test
    public void tempLessThan108() {
        Temperature test = new Temperature(107.9);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(5), test.getResult());
    }

    @Test
    public void tempMoreThan1031() {
        Temperature test = new Temperature(103.2);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(5), test.getResult());
    }

    @Test
    public void temp1031() {
        Temperature test = new Temperature(103.1);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(4), test.getResult());
    }

    @Test
    public void tempLessThan1031() {
        Temperature test = new Temperature(103.0);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(4), test.getResult());
    }

    @Test
    public void tempMoreThan1005() {
        Temperature test = new Temperature(100.6);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(4), test.getResult());
    }

    @Test
    public void temp1005() {
        Temperature test = new Temperature(100.5);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(3), test.getResult());
    }

    @Test
    public void tempLessThan1005() {
        Temperature test = new Temperature(100.4);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(3), test.getResult());
    }

    @Test
    public void tempMoreThan989() {
        Temperature test = new Temperature(99);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(3), test.getResult());
    }

    @Test
    public void temp989() {
        Temperature test = new Temperature(98.9);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }

    @Test
    public void tempLessThan989() {
        Temperature test = new Temperature(98.8);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }

    @Test
    public void tempMoreThan97() {
        Temperature test = new Temperature(97.1);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }

    @Test
    public void temp97() {
        Temperature test = new Temperature(97);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }

    @Test
    public void tempLessThan97() {
        Temperature test = new Temperature(96.9);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
    }

    @Test
    public void tempMoreThan93() {
        Temperature test = new Temperature(93.1);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
    }
    
    @Test
    public void temp93() {
        Temperature test = new Temperature(93);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
    }

    @Test
    public void tempLessThan93() {
        Temperature test = new Temperature(92.9);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(0), test.getResult());
    }
}