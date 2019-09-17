package com.philips.casestudy.dal;

import com.philips.casestudy.domain.Bed;
import java.util.List;

public interface BedDAO {
    Bed addBed(Bed bed);
    List<Bed> findAll(); // display all beds in an ICU
    Bed findBed(int bedId);
    void deleteBed(int bedId);
    void update(Bed bed);
}
