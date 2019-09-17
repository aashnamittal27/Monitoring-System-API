package com.philips.casestudy.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.utils.GenericUtils;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class JpaBedDAO implements BedDAO {

    @PersistenceContext
    EntityManager em;

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }

    @Override
    public Bed addBed(Bed bed) {
        em.persist(bed);
        return bed;
    }

    @Override
    public List<Bed> findAll() {
        return GenericUtils.castList(Bed.class, em.createQuery("select b from Bed b").getResultList());
    }

    @Override
    public Bed findBed(int bedId) {
        return em.find(Bed.class, bedId);
    }

    @Override
    public void deleteBed(int bedId) { // deleting a bed and updating the list of beds in ICU
        em.createQuery("delete from Bed b where b.bedId = :paramId").setParameter("paramId", bedId).executeUpdate();
    }

    @Override
    public void update(Bed bed) {
        if (bed == null) return;
        em.merge(bed);
    }
}