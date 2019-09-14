package com.philips.casestudy.casestudy.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.service.BedService;
import com.philips.casestudy.web.BedController;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BedControllerTest {

    @Test
    public void addBed_ValidAdd() throws Exception {
        BedController bedcontroller = new BedController();
        BedService bedService = Mockito.mock(BedService.class);
        bedcontroller.setBedService(bedService);

        Bed bed = new Bed(true);
        bed.setBedId(1);

        List<Bed> beds = new ArrayList<>();

        Mockito.when(bedcontroller.getAllBeds()).thenReturn(beds);
        Mockito.when(bedService.addNewBed(bed)).thenReturn(1);

        ResponseEntity<Bed> response = bedcontroller.addBed();

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        
    }

    @Test
    public void addBed_BeyondCapacityAdd() throws Exception {

        BedController bedcontroller = new BedController();
        BedService bedService = Mockito.mock(BedService.class);
        bedcontroller.setBedService(bedService);

        List<Bed> beds = new ArrayList<>();
        for(int i=0;i<10;i++){
            beds.add(new Bed(true));
        }

        Bed bed = new Bed(true);
        bed.setBedId(11);

        Mockito.when(bedcontroller.getAllBeds()).thenReturn(beds);

        ResponseEntity<Bed> response = bedcontroller.addBed();

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    }

    

    @Test
    public void getBedByID_ValidID() throws Exception {

        BedController bedcontroller = new BedController();
        BedService bedService = Mockito.mock(BedService.class);
        bedcontroller.setBedService(bedService);

        Bed bed = new Bed(true);
        bed.setBedId(1);

        Mockito.when(bedService.findBed(1)).thenReturn(bed);

        ResponseEntity<Bed> response = bedcontroller.getBedById(1);

        if(response.getStatusCode() == HttpStatus.OK) {
            assertEquals(response.getBody(), bed);
        } 
    }

    @Test
    public void getBedById_InvalidID() throws Exception {
        BedController bedController = new BedController();
        BedService bedService = Mockito.mock(BedService.class);
        bedController.setBedService(bedService);

        Bed bed = new Bed(true);
        bed.setBedId(1);

        Mockito.when(bedService.findBed(1)).thenReturn(bed);
        Bed actual = null;

        ResponseEntity<Bed> response = bedController.getBedById(11);

        if(response.getStatusCode() == HttpStatus.NOT_FOUND) {
            // assertEquals(response.getBody(), );
            assertEquals(null, actual);
        }
    }

    @Test
    public void getAllBeds_ContainingBeds() throws Exception {
        BedController bedController = new BedController();
        BedService bedService = Mockito.mock(BedService.class);
        bedController.setBedService(bedService);

        Bed bed = new Bed(true);
        bed.setBedId(1);

        List<Bed> bedList = new ArrayList<>();
        bedList.add(bed);

        Mockito.when(bedService.getAllBeds()).thenReturn(bedList);

        List<Bed> responseList = bedController.getAllBeds();
      
        assertTrue(responseList == bedList);
        
    }

    @Test
    public void getAllBeds_EmptyList() throws Exception {
        BedController bedController = new BedController();
        BedService bedService = Mockito.mock(BedService.class);
        bedController.setBedService(bedService);

        List<Bed> bedList = new ArrayList<>();
        Mockito.when(bedService.getAllBeds()).thenReturn(bedList);

        List<Bed> responseList = bedController.getAllBeds();
      
        assertTrue(responseList == bedList);

    }


}