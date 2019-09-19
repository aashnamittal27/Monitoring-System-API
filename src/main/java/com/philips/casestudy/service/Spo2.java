package com.philips.casestudy.service;

public class Spo2 implements MonitoringVitals {

  String result;
  private final double reading;
  private static final double UPPER_HEALTHY_READING = 100;
  private static final double UPPER_ACCEPTABLE_READING = 95;
  private static final double LOWER_ACCEPTABLE_READING = 90;
  private static final double LOWER_UNSAFE_LEVEL_READING = 70;

  public Spo2(double reading) {
    this.reading = reading;
    result = null;
  }

  @Override
  public void vitalChecker() {

    if (this.reading > UPPER_HEALTHY_READING) {

      result = monitorStatus.get(6);
    } else if (this.reading > UPPER_ACCEPTABLE_READING ) {

      result = monitorStatus.get(2);
    } else if (this.reading > LOWER_ACCEPTABLE_READING ) {

      result = monitorStatus.get(2);
    } else if (this.reading >= LOWER_UNSAFE_LEVEL_READING ) {

      result = monitorStatus.get(1);
    } else {


      result = monitorStatus.get(0);
    }

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

}