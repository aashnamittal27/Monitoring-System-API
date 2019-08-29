package com.philips.casestudy.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.philips.casestudy.domain.NursingStation;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class JpaNursingStationDAO implements NursingStationDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public NursingStation save(NursingStation station) {
        em.persist(station);
        return station;
    }

    @Override
    public List<NursingStation> findAll() {

        Query q = em.createQuery("select ns from NursingStation ns");
        List<NursingStation> nursingStations = q.getResultList(); // https://developer.jboss.org/thread/3163 warning
                                                                  // resolution
        return nursingStations;
    }

    @Override
    public NursingStation findById(int stationId) {
        return em.find(NursingStation.class, stationId);
    }

    @Override
    public void deletebyId(int stationId) { // we want to delete all beds if we delete ICU

        /* first we should delete all the beds within that ICU */

        NursingStation station = findById(stationId);
        if (station.getBeds().size() > 0) {
            // List<Bed> beds= checkBedRecords.getResultList();
            Query bedQ = em.createQuery("delete from bed b where b.stationId = :paramID"); // check if it will be station_id ?????
            bedQ.setParameter("paramId", stationId);
            bedQ.executeUpdate();
        }

        /* then we delete the ICU itself */
        em.createQuery("delete from NursingStation ns where ns.stationId = :Id")
                .setParameter("Id", stationId).executeUpdate(); //
        // although in class definition the field name is stationId but sql makes
        // camelcase to station_id
    }

}