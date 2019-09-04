package com.philips.casestudy.domain;

public class Temperature implements MonitoringVitals{

    private double reading;
	private final double minFeverValue=93;
	private final double lowerNormalVal = 97;
	private final double upperNormalVal = 98.9;
	private final double upperAcceptableFeverReading = 100.5;
	private final double upperConcernFeverReading = 103.1;
    private final double maxFeverValue=108;
    String result;

    public Temperature(double reading) {
        this.reading = reading;
        result = null;
    }
    
    @Override
    public void vitalChecker() {

        if(this.reading < minFeverValue) {
			// result="Undetectable Temperature";
			result = monitorStatus.get(0);
		}
		else if (this.reading < lowerNormalVal && this.reading>=minFeverValue) {
			// result = "Unacceptably low temperature level";
			result = monitorStatus.get(1);
		}
		else if(this.reading >= lowerNormalVal && this.reading <= upperNormalVal) {
			// result = "Normal body temperature";
			result = monitorStatus.get(2);
		}
		else if(this.reading > upperNormalVal && this.reading <= upperAcceptableFeverReading) {
			// result = "Care needed - minor fever";
			result = monitorStatus.get(3);
		}
		else if(this.reading > upperAcceptableFeverReading && this.reading <= upperConcernFeverReading) {
			// result = "Extreme care needed - High Fever";
			result = monitorStatus.get(4);
		}
		else if(this.reading > upperConcernFeverReading && this.reading <= maxFeverValue) {
			// result = "Critical care needed - Very High Fever";
			result = monitorStatus.get(5);
		}
		else
		{
		// result = "Device is not calibrated to measure fever more than 108";
			result = monitorStatus.get(6);
		}
		// display();
		// return result;
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
		return "Temperature [reading=" + reading + ", result=" + result + "]";
	}
	public String getResult() {
		return result;
	}
}