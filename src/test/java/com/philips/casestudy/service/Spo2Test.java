package com.philips.casestudy.service;

import static org.junit.Assert.assertEquals;
import com.philips.casestudy.service.Spo2;
import org.junit.Test;

public class Spo2Test {
    @Test
    public void spo2LevelMoreThan100() {
        Spo2 test = new Spo2(100.1);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(6), test.getResult());
    }

    @Test
    public void spo2Level100() {
        Spo2 test = new Spo2(100);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
    }

    @Test
    public void spo2LevelLessThan100() {
        Spo2 test = new Spo2(99);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
        
    }

    @Test
    public void spo2LevelMoreThan95() {
        Spo2 test = new Spo2(96);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
        
    }

    @Test
    public void spo2Level95() {
        Spo2 test = new Spo2(95);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
        
    }

    @Test
    public void spo2LevelLessThan95() {
        Spo2 test = new Spo2(94);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
        
    }

    @Test
    public void spo2LevelMoreThan90() {
        Spo2 test = new Spo2(91);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(2), test.getResult());
        
    }

    @Test
    public void spo2Level90() {
        Spo2 test = new Spo2(90);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
        
    }

    @Test
    public void spo2LevelLessThan90() {
        Spo2 test = new Spo2(89);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
        
    }

    @Test
    public void spo2LevelMoreThan70() {
        Spo2 test = new Spo2(71);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
        
    }
    
    @Test
    public void spo2Level70() {
        Spo2 test = new Spo2(70);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(1), test.getResult());
        
    }

    @Test
    public void spo2LevelLessThan70() {
        Spo2 test = new Spo2(69);
        test.vitalChecker();
        assertEquals(MonitoringVitals.monitorStatus.get(0), test.getResult());
        
    }
}
