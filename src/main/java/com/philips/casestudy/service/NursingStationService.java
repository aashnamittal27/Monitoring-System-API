package com.philips.casestudy.service;

import java.util.List;

import com.philips.casestudy.domain.NursingStation;

public interface NursingStationService {
    
    int addNewStation(NursingStation station);
    List<NursingStation> getAllStations();
    NursingStation findStation(int id);
    void deleteExistingStation(int id);
}