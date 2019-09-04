package com.philips.casestudy.service;

import java.util.List;

import com.philips.casestudy.domain.MonitoringVitals;

public interface VitalServiceRandom {
    List<MonitoringVitals> initialiseVitals();
    int generateRandomIntegerForVitals(int minValue, int maxValue);
    double generateRandomDoubleForVitals(double minValue, double maxValue);
    List<MonitoringVitals> generateAlertingStream(List<MonitoringVitals> vitals);
}