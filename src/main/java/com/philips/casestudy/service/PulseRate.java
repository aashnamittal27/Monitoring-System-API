package com.philips.casestudy.service;

import com.philips.casestudy.service.MonitoringVitals;

public class PulseRate implements MonitoringVitals {

	int reading;
	String result;
	private final int lowestPulseRate=30;
	private final int lowerSleepingRate = 40;
	private final int upperSleepingRate = 60;
	private final int upperRestingRate = 100;
	private final int upperExerciseRate = 220;
    private final int highestPulseRate=254;

    
    public PulseRate(int reading) { // constructor
		this.reading = reading;
		result = null;
	}
	
    @Override
    public void vitalChecker() {
        if(this.reading < lowestPulseRate)
		{
			// result= "Device can't measure pulse rate lower than 30";
			result = monitorStatus.get(0);
		}
		else if(this.reading >= lowestPulseRate && this.reading < lowerSleepingRate) {
			// result = "Below healthy resting pulse rates.";
			result = monitorStatus.get(1);
		}
		else if(this.reading >= lowerSleepingRate && this.reading < upperSleepingRate) {
			// result = "Normal pulse rate for sleeping.";
			result = monitorStatus.get(2);
		}
		else if(this.reading >= upperSleepingRate && this.reading < upperRestingRate) {
			// result = "Healthy adult resting pulserate.";
			result = monitorStatus.get(2);
		}
		else if(this.reading >= upperRestingRate && this.reading <= upperExerciseRate) {
			// result = "Acceptable if measured during exercise but Unacceptable resting pulserate";
			result = monitorStatus.get(2);
		}
		else if(this.reading > upperExerciseRate && this.reading <= highestPulseRate){
			// result = "Abnormally high pulse rate";
			result = monitorStatus.get(3);
		}
		else {
			// result="Device does not detect pulse rates > 254 BPM ";
			result = monitorStatus.get(6);
		}
		// display();
		// return result;
    }
    
	public double getReading() {
        return reading;
    }

	public String getResult() {
		return result;
	}
}