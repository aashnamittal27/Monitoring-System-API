package com.philips.casestudy.web;

import java.net.URI;
import java.util.List;

import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.service.BedService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/*
    int addNewBed(Bed bed, int stationId);
    List<Bed> getAllBeds(int stationId);
    Bed findBed(int bedId);
    void deleteExistingBed(int bedId);*/
@RestController
public class BedController {

    BedService bedService;
    
    @Autowired
    public void setBedService(BedService bedService) {
        this.bedService = bedService;
    }

    int bedLimit=10;

    // @RequestMapping(value = "/api/beds", method = RequestMethod.POST)
    // public ResponseEntity<Bed> addStation(@RequestBody Bed bed){
    //     try{
    //         int id = bedService.addNewBed(bed, bed.getStation().getStationId());
    //         HttpHeaders headers = new HttpHeaders();
    //         headers.setLocation(URI.create("/api/beds/"+id));
    //         return new ResponseEntity<>(headers, HttpStatus.CREATED);
    //     }
    //     catch (Exception e) {
    //         e.printStackTrace();
    //         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    //     }
    // }

    @RequestMapping(value = "/beds", method = RequestMethod.POST)
    public ResponseEntity<Bed> addBed(){

        Bed bed = new Bed(true);
        int size = getAllBeds().size();
        if(size==bedLimit)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        try{
            int id = bedService.addNewBed(bed);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/beds/"+id));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // @RequestMapping(value = "/api/beds", method = RequestMethod.GET)
    // public List<Bed> getAllStations(@PathVariable("id") int stationId){
    //     return bedService.getAllBeds();
    // }
    @RequestMapping(value = "/beds", method = RequestMethod.GET)
    public List<Bed> getAllBeds()
    {
        return bedService.getAllBeds();
    }

    @RequestMapping(value = "/beds/{bid}", method = RequestMethod.GET)
    public ResponseEntity<Bed> getBedById(@PathVariable("bid")int id){
        
        Bed bed = bedService.findBed(id);

        if(bed!=null){
            return new ResponseEntity<>(bed, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // @RequestMapping(value = "/beds/{id}", method = RequestMethod.DELETE)
    // public ResponseEntity<Bed> deleteStation(@PathVariable("id")int id){

    //     Bed bed = bedService.findBed(id);
    //     if(bed!=null){
    //         bedService.deleteExistingBed(id);
    //         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    //     }
    //     else{
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //     }
    // }
}