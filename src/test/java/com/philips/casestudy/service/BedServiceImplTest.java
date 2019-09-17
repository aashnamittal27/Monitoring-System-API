package com.philips.casestudy.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.philips.casestudy.dal.BedDAO;
import com.philips.casestudy.domain.Bed;

import org.junit.Test;
import org.mockito.Mockito;

public class BedServiceImplTest {

    @Test
    public void addNewBedTest() {
        Bed bed = new Bed();
        BedServiceImpl bsi = new BedServiceImpl();
        BedDAO dao = Mockito.mock(BedDAO.class);
        Mockito.when(dao.addBed(bed)).thenReturn(bed);
        bsi.setBedDao(dao);

        Bed refBed = bsi.addNewBed(bed);
        assertTrue(refBed.getisAvailable());
    }

    @Test
    public void getAllBedsTest() {
        List<Bed> beds = List.of(new Bed(), new Bed());
        BedServiceImpl bsi = new BedServiceImpl();
        BedDAO dao = Mockito.mock(BedDAO.class);
        Mockito.when(dao.findAll()).thenReturn(beds);
        bsi.setBedDao(dao);
        beds.containsAll(bsi.getAllBeds());
    }

    @Test
    public void findBedTest() {
        Bed bed = new Bed();
        BedServiceImpl bsi = new BedServiceImpl();
        BedDAO dao = Mockito.mock(BedDAO.class);
        Mockito.when(dao.findBed(1)).thenReturn(bed);
        bsi.setBedDao(dao);

        assertEquals(bed, bsi.findBed(1));
    }

    @Test
    public void deletingNonExistingBedReturnsFalse() {
        BedServiceImpl bsi = new BedServiceImpl();
        BedDAO dao = Mockito.mock(BedDAO.class);
        Mockito.when(dao.findBed(1)).thenReturn(null);
        bsi.setBedDao(dao);

        assertFalse(bsi.deleteExistingBed(1));
    }

    @Test
    public void deletingAnUnavailableBedReturnsFalse() {
        BedServiceImpl bsi = new BedServiceImpl();
        BedDAO dao = Mockito.mock(BedDAO.class);
        Mockito.when(dao.findBed(1)).thenReturn(new Bed(false));
        bsi.setBedDao(dao);

        assertFalse(bsi.deleteExistingBed(1));
    }

    @Test
    public void deletingExistingBedReturnsTrue() {
        Bed bed = new Bed(true);
        bed.setBedId(1);

        BedServiceImpl bsi = new BedServiceImpl();
        BedDAO dao = Mockito.mock(BedDAO.class);
        Mockito.when(dao.findBed(1)).thenReturn(new Bed(true));
        bsi.setBedDao(dao);

        assertTrue(bsi.deleteExistingBed(1));
    }

}