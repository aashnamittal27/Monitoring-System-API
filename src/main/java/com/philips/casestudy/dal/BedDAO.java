package com.philips.casestudy.dal;

import com.philips.casestudy.domain.Bed;
import java.util.List;

public interface BedDAO {

    Bed save(Bed bed,int stationId);
    List<Bed> findAll(int stationId); // display all beds in an ICU
    Bed findById(int bedId);
    void deletebyId(int bedId);

}
