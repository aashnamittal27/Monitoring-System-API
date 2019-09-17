package com.philips.casestudy.dal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.philips.casestudy.domain.Bed;

import org.junit.Test;
import org.mockito.Mockito;

public class JpaBedDAOTest {
    @Test
    public void addBedTest() {
        EntityManager em = Mockito.mock(EntityManager.class);
        JpaBedDAO dao = new JpaBedDAO();
        dao.setEntityManager(em);
        Bed bed = new Bed();
        assertEquals(bed, dao.addBed(bed));
    }

    @Test
    public void findBedTest() {
        Bed bed = new Bed();
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(em.find(Bed.class, 1)).thenReturn(bed);
        JpaBedDAO dao = new JpaBedDAO();
        dao.setEntityManager(em);
        assertEquals(bed, dao.findBed(1));
    }

    @Test
    public void findAllTest() {
        List<Object> beds = List.of(new Bed(), new Bed());
        Query query = Mockito.mock(Query.class);
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.getResultList()).thenReturn(beds);
        JpaBedDAO dao = new JpaBedDAO();
        dao.setEntityManager(em);
        assertTrue(beds.containsAll(dao.findAll()));
    }

    @Test
    public void deleteBedTest() {
        Query query = Mockito.mock(Query.class);
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(em.createQuery(Mockito.anyString())).thenReturn(query);
        Mockito.when(query.setParameter(Mockito.anyString(), Mockito.anyInt())).thenReturn(query);
        JpaBedDAO dao = new JpaBedDAO();
        dao.setEntityManager(em);
        dao.deleteBed(1);
    }

    @Test
    public void updateBed1() {
        Bed bed = new Bed();
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(em.merge(bed)).thenReturn(bed);

        JpaBedDAO bedDao = new JpaBedDAO();
        bedDao.setEntityManager(em);

        bedDao.update(bed);
    }

    @Test
    public void updateBed2() {
        EntityManager em = Mockito.mock(EntityManager.class);
        Mockito.when(em.merge(null)).thenReturn(null);

        JpaBedDAO bedDao = new JpaBedDAO();
        bedDao.setEntityManager(em);

        bedDao.update(null);
    }

}