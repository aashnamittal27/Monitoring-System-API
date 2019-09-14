package com.philips.casestudy.service;

import java.util.List;

import com.philips.casestudy.domain.Bed;

public interface BedService {

    // int addNewBed(Bed bed, int stationId);
    int addNewBed(Bed bed);
    List<Bed> getAllBeds();
    Bed findBed(int bedId);
    void deleteExistingBed(int bedId);
}