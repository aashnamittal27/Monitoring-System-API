package com.philips.casestudy.service;

import java.util.List;

import com.philips.casestudy.domain.Bed;

public interface BedService {

    int addNewBed(Bed bed, int stationId);
    List<Bed> getAllBeds(int stationId);
    Bed findBed(int bedId);
    void deleteExistingBed(int bedId);
}