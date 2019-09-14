package com.philips.casestudy.domain;

public class Spo2 implements MonitoringVitals {

    String result;
    private double reading;
    private final double upperHealthyReading = 100;
    private final double upperAcceptableReading = 95;
    private final double lowerAcceptableReading = 90;
    private final double lowerUnsafeLevelReading = 70;
    String vitalName = "Spo2";


    public Spo2(double reading) {
        this.reading = reading;
        result = null;
    }

    @Override
    public void vitalChecker() {

        if (this.reading > upperHealthyReading) {
            // result="Undetectable pulse Rate";
            result = monitorStatus.get(6);
        } else if (this.reading > upperAcceptableReading && this.reading <= upperHealthyReading) {
            // result = "Normal healthy individual spo2 level";
            result = monitorStatus.get(2);
        } else if (this.reading > lowerAcceptableReading && this.reading <= upperAcceptableReading) {
            // result = "SPO2 level clinically acceptable but patient may be smoker";
            result = monitorStatus.get(2);
        } else if (this.reading >= lowerUnsafeLevelReading && this.reading <= lowerAcceptableReading) {
            // result = "Unhealthy and unsafe SPO2 level indicates Hypoxemia, ";
            result = monitorStatus.get(1);
        } else {
            // result="Device is not calibrated to measure spo2 lower than 70 Extreme lack
            // of oxygen, ischemic diseases may occur ";
            result = monitorStatus.get(0);
        }
		// display();
    }

    @Override
    public void display() {
        System.out.println(result);
    }

    public double getReading() {
        return reading;
    }

    @Override
    public String toString() {
        return "Spo2 [reading=" + reading + ", result=" + result + "]";
    }

    public String getResult() {
		return result;
    }
    
    public String getVitalName() {
		return vitalName;
	}

}