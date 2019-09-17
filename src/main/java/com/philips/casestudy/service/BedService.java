package com.philips.casestudy.service;

import java.util.List;

import com.philips.casestudy.domain.Bed;

public interface BedService {
    Bed addNewBed(Bed bed);
    List<Bed> getAllBeds();
    Bed findBed(int bedId);
    boolean deleteExistingBed(int bedId);
}