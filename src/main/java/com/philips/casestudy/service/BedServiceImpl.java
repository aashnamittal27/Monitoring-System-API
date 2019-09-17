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

    public void setBedDao(BedDAO bedDao) {
        this.bedDao = bedDao;
    }

    @Override
    public Bed addNewBed(Bed bed) {
        bed.setisAvailable(true);
        return bedDao.addBed(bed);
    }

    @Override
    public List<Bed> getAllBeds() {
        return bedDao.findAll(); 
    }

    @Override
    public Bed findBed(int bedId) {
        return bedDao.findBed(bedId);
    }

    @Override
    public boolean deleteExistingBed(int bedId) {
        Bed bed = bedDao.findBed(bedId);
        if (bed == null) return false;

        if (bed.getisAvailable()) {
            bedDao.deleteBed(bedId);
            return true;
        }

        return false;
    }
}