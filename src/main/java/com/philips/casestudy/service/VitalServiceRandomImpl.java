package com.philips.casestudy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

@Service
public class VitalServiceRandomImpl  {

    public List<MonitoringVitals> initialiseVitals() { // objects created
        MonitoringVitals pulseRate =(MonitoringVitals)new PulseRate(generateRandomIntegerForVitals(28, 257));
        MonitoringVitals spo2 = (MonitoringVitals)new Spo2(generateRandomDoubleForVitals(65, 100));
        MonitoringVitals temperature = (MonitoringVitals)new Temperature(generateRandomDoubleForVitals(92, 109));

        List<MonitoringVitals> vitals = new ArrayList<MonitoringVitals>() {
            private static final long serialVersionUID = 1L;

            {
                add(pulseRate);
                add(spo2);
                add(temperature);
            }
        };
        List<MonitoringVitals> newList = generateAlertingStream(vitals);
        System.out.println("--------");
        for(MonitoringVitals vital: vitals)
        System.out.println(vital);
        return newList;

        // ObjectMapper obj = new ObjectMapper();
        // try {

        //     for(MonitoringVitals vital: vitals){
        //         String jsonStr = obj.writeValueAsString(vitals);
        //     }
        //     return jsonStr;
        // }

        // catch (IOException e) {
        //     e.printStackTrace();
        // }

    }

    public int generateRandomIntegerForVitals(int minValue, int maxValue) {
        // int minPulse=28;
        // int maxPulse=257;
        // double minTemp=92;
        // double maxTemp=109;
        // double minSpo2=65;
        // double maxSpo2=100;;
        // JSONObject jo = new JSONObject();

        return ThreadLocalRandom.current().nextInt(minValue, maxValue);

        // jo.put("SPO2", ThreadLocalRandom.current().nextDouble(minSpo2, maxSpo2));
        // jo.put("temperature", ThreadLocalRandom.current().nextDouble(minTemp,
        // maxTemp));
        // jo.put("pulse rate", ThreadLocalRandom.current().nextInt(minPulse,
        // maxPulse));
        // return jo.toJSONString();
    }

    public double generateRandomDoubleForVitals(double minValue, double maxValue) {
        return ThreadLocalRandom.current().nextDouble(minValue, maxValue);
    }

    // ValidatorService();

    public List<MonitoringVitals> generateAlertingStream(List<MonitoringVitals> vitals) {

        List<MonitoringVitals> alertList = new ArrayList<>();
        for (MonitoringVitals vital : vitals) {
            vital.vitalChecker();
            alertList.add(vital);
            System.out.println(vital);
        }

        return alertList;
    }

    
}