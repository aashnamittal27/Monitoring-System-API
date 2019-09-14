package com.philips.casestudy.service;

import java.util.List;

import com.philips.casestudy.dal.BedDAO;
import com.philips.casestudy.domain.Bed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BedServiceImpl implements BedService {

    @Autowired
    BedDAO bedDao;

    // @Autowired
    // NursingStationDAO stationDao;

/*    @Override
    public int addNewBed(Bed bed, int stationId) { // before adding a new Bed check if the ICU can accomodate a bed

        //NursingStation station = // get the station by DAO **********
        List<Bed> beds = getAllBeds(stationId);

        if (beds.size() < stationDao.findById(stationId).getBedCapacity()) { 
            Bed savedBed = bedDao.save(bed, stationId);
            return savedBed.getBedId();
        } else {
            throw new IllegalAddException(
                    "No more beds can be added to the nursing station, try increasing station capacity first");
        }
    }*/

     @Override
    public int addNewBed(Bed bed) {

        Bed savedBed = bedDao.save(bed);
        return savedBed.getBedId();
        
    }

    // @Override
    // public List<Bed> getAllBeds(int stationId) {
    //     return bedDao.findAll(stationId); // change
    // }

    public List<Bed> getAllBeds(){
        return bedDao.findAll();
    }

    @Override
    public Bed findBed(int bedId) {
        return bedDao.findById(bedId);
    }

    @Override
    public void deleteExistingBed(int bedId) {

        Bed bed = bedDao.findById(bedId);
        if(bed.getisAvailable())
            bedDao.deletebyId(bedId);
		else
			try {
				throw new Exception("Bed can't be deleted");
			} catch (Exception e) {
				e.printStackTrace();
			}
    }
}