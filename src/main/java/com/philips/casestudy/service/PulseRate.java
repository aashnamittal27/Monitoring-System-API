package com.philips.casestudy.service;

public class PulseRate implements MonitoringVitals {

  int reading;
  String result;
  private static final int LOWEST_PULSE_RATE=30;
  private static final int LOWER_SLEEPING_RATE = 40;
  private static final int UPPER_SLEEPING_RATE = 60;
  private static final int UPPER_RESTING_RATE = 100;
  private static final int UPPER_EXERCISE_RATE = 220;
  private static final int HIGHEST_PULSE_RATE=254;


  public PulseRate(int reading) {
    this.reading = reading;
    result = null;
  }

  @Override
  public void vitalChecker() {
    if(this.reading < LOWEST_PULSE_RATE)
    {

      result = monitorStatus.get(0);
    }
    else if(this.reading < LOWER_SLEEPING_RATE) {

      result = monitorStatus.get(1);
    }
    else if(this.reading < UPPER_SLEEPING_RATE) {

      result = monitorStatus.get(2);
    }
    else if(this.reading < UPPER_RESTING_RATE) {

      result = monitorStatus.get(2);
    }
    else if(this.reading <= UPPER_EXERCISE_RATE) {

      result = monitorStatus.get(2);
    }
    else if(this.reading <= HIGHEST_PULSE_RATE){

      result = monitorStatus.get(3);
    }
    else {

      result = monitorStatus.get(6);
    }


  }

  public double getReading() {
    return reading;
  }

  public String getResult() {
    return result;
  }
}