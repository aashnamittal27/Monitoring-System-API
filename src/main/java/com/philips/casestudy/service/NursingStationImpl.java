package com.philips.casestudy.service;

import java.util.List;

import com.philips.casestudy.dal.NursingStationDAO;
import com.philips.casestudy.domain.NursingStation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NursingStationImpl implements NursingStationService {

    @Autowired
    NursingStationDAO stationDao;

    @Override
    public int addNewStation(NursingStation station) {
        NursingStation savedStation = stationDao.save(station);
        return savedStation.getStationId();

    }

    @Override
    public List<NursingStation> getAllStations() {
        return stationDao.findAll();
    }

    @Override
    public NursingStation findStation(int id) {
        return stationDao.findById(id);
    }

    @Override
    public void deleteExistingStation(int id) {
        stationDao.deletebyId(id);
    }

}