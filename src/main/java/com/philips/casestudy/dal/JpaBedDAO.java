package com.philips.casestudy.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.domain.NursingStation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class JpaBedDAO implements BedDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public Bed save(Bed bed, int stationId) {
       
        NursingStation nursingStation = em.find(NursingStation.class, stationId);
        bed.setStation(nursingStation);
        em.persist(bed);
        return bed;
    }

    @Override
    public List<Bed> findAll(int stationId) {
        // we want to fetch all beds within an ICU
        Query q = em.createQuery("select b from Bed b where b.stationId = :paramId").setParameter("paramId", stationId);
        List<Bed> beds = q.getResultList();
        return beds;
    }

    @Override
    public Bed findById(int bedId) {
        return em.find(Bed.class, bedId);
    }

    @Override
	public void deletebyId(int bedId) { // deleting a bed and updating the list of beds in ICU
        Bed bed = findById(bedId);
        NursingStation station = bed.getStation();
        station.removeBed(bed);
        em.createQuery("delete b from Bed b where b.bedId = :paramId").setParameter("paramId", bedId).executeUpdate();
        
	}

}