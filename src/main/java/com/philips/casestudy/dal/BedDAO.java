package com.philips.casestudy.dal;

import com.philips.casestudy.domain.Bed;
import java.util.List;

public interface BedDAO {

    // Bed save(Bed bed,int stationId);
    Bed save(Bed bed);
    // List<Bed> findAll(int stationId); // display all beds in an ICU
    List<Bed> findAll();
    Bed findById(int bedId);
    void deletebyId(int bedId);

}
