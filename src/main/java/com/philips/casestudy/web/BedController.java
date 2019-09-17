package com.philips.casestudy.web;

import java.net.URI;
import java.util.ArrayList;
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

@RestController
public class BedController {

    @Autowired
    BedService bedService;

    public void setBedService(BedService bedService) {
        this.bedService = bedService;
    }

    @RequestMapping(value = "/bed", method = RequestMethod.POST)
    public ResponseEntity<Bed> addBed(){
        Bed bed = new Bed(true);
        Bed newBed = bedService.addNewBed(bed);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/bed/" + newBed.getBedId()));
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/bed", method = RequestMethod.GET)
    public ResponseEntity<List<Bed>> getAllBeds(){
        List<Bed> beds = bedService.getAllBeds();
        if (beds == null) {
            return new ResponseEntity<>(new ArrayList<Bed>(), HttpStatus.OK);
        }
        return new ResponseEntity<>(beds, HttpStatus.OK);
    }

    @RequestMapping(value = "/bed/{id}", method = RequestMethod.GET)
    public ResponseEntity<Bed> getBedById(@PathVariable("id") int id){
        
        Bed bed = bedService.findBed(id);

        if (bed != null){
            return new ResponseEntity<>(bed, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/bed/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Bed> deleteBed(@PathVariable("id") int id) {
        Bed bed = bedService.findBed(id);
        if (bed != null){
            bedService.deleteExistingBed(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}