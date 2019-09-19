package com.philips.casestudy.service;

public class Temperature implements MonitoringVitals {

  private final double reading;
  private static final double MIN_FEVER_VALUE=93;
  private static final double LOWER_NORMAL_VAL = 97;
  private static final double UPPER_NORMAL_VAL = 98.9;
  private static final double UPPER_ACCEPATBLE_FEVER_READING = 100.5;
  private static final double UPPER_CONCERN_FEVER_READING = 103.1;
  private static final double MAX_FEVER_VALUE=108;
  String result;

  public Temperature(double reading) {
    this.reading = reading;
    result = null;
  }

  @Override
  public void vitalChecker() {

    if(this.reading < MIN_FEVER_VALUE) {

      result = monitorStatus.get(0);
    }
    else if (this.reading < LOWER_NORMAL_VAL ) {

      result = monitorStatus.get(1);
    }
    else if(this.reading <= UPPER_NORMAL_VAL) {

      result = monitorStatus.get(2);
    }
    else if(this.reading <= UPPER_ACCEPATBLE_FEVER_READING) {

      result = monitorStatus.get(3);
    }
    else if(this.reading <= UPPER_CONCERN_FEVER_READING) {

      result = monitorStatus.get(4);
    }
    else if(this.reading <= MAX_FEVER_VALUE) {

      result = monitorStatus.get(5);
    }
    else
    {

      result = monitorStatus.get(6);
    }

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