package com.philips.casestudy.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.service.BedService;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BedControllerTest {

    @Test
    public void addBedTest() {
        Bed bed = new Bed();
        bed.setBedId(1);
        BedService bs = Mockito.mock(BedService.class);
        Mockito.when(bs.addNewBed(Mockito.any(Bed.class))).thenReturn(bed);
        BedController bc = new BedController();
        bc.setBedService(bs);

        ResponseEntity<Bed> response = bc.addBed();
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("/bed/1", response.getHeaders().getLocation().toString());
    }

    @Test
    public void getAllBedsReturnsEmptyListWhenThereAreNoBeds() {
        BedService bs = Mockito.mock(BedService.class);
        Mockito.when(bs.getAllBeds()).thenReturn(null);
        BedController bc = new BedController();
        bc.setBedService(bs);

        ResponseEntity<List<Bed>> response = bc.getAllBeds();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().size() == 0);
    }

    @Test
    public void getAllBedsReturnsListOfBedsWhenThereAreBeds() {
        List<Bed> beds = List.of(new Bed(), new Bed());
        BedService bs = Mockito.mock(BedService.class);
        Mockito.when(bs.getAllBeds()).thenReturn(beds);
        BedController bc = new BedController();
        bc.setBedService(bs);

        ResponseEntity<List<Bed>> response = bc.getAllBeds();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().containsAll(beds));
    }

    @Test
    public void getBedByIdReturnsNotFoundForInvalidId() {
        BedService bs = Mockito.mock(BedService.class);
        Mockito.when(bs.findBed(Mockito.anyInt())).thenReturn(null);
        BedController bc = new BedController();
        bc.setBedService(bs);

        ResponseEntity<Bed> response = bc.getBedById(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void getBedByIdReturnsBedForValidId() {
        BedService bs = Mockito.mock(BedService.class);
        Bed bed = new Bed();
        Mockito.when(bs.findBed(1)).thenReturn(bed);
        BedController bc = new BedController();
        bc.setBedService(bs);

        ResponseEntity<Bed> response = bc.getBedById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bed, response.getBody());
    }

    @Test
    public void deleteBedReturnsNoContentIfBedIsDeleted() {
        BedService bs = Mockito.mock(BedService.class);
        Bed bed = new Bed();
        Mockito.when(bs.findBed(1)).thenReturn(bed);
        Mockito.when(bs.deleteExistingBed(1)).thenReturn(true);
        BedController bc = new BedController();
        bc.setBedService(bs);

        ResponseEntity<Bed> response = bc.deleteBed(1);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void deleteBedReturnsBadRequestForInvalidId() {
        BedService bs = Mockito.mock(BedService.class);
        Mockito.when(bs.findBed(1)).thenReturn(null);
        BedController bc = new BedController();
        bc.setBedService(bs);

        ResponseEntity<Bed> response = bc.deleteBed(1);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}