package com.philips.casestudy.dal;

import com.philips.casestudy.domain.NursingStation;
import java.util.List;

public interface NursingStationDAO {
    NursingStation  save(NursingStation station);
    List<NursingStation> findAll();
    NursingStation findById(int stationId);
    void deletebyId(int bedId); // we want to delete all beds if we delete ICU
}
