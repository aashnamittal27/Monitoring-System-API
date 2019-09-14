package com.philips.casestudy.domain;

import java.util.ArrayList;
import java.util.List;

public interface MonitoringVitals {

    public List<String> monitorStatus = new ArrayList<String>() {
        private static final long serialVersionUID = 1L;

        {
            add("undetectably low reading");
            add("low reading - Care needed");
            add("Normal");
            add("High reading - Care needed");
            add("High reading - Extreme care needed");
            add("High reading - Critical care needed");
            add("Device not calibrated to measure such high values");
        }
    };

    public void vitalChecker();

    public void display();
}