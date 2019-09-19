package com.philips.casestudy.web;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.philips.casestudy.domain.Bed;
import com.philips.casestudy.service.BedService;

@RestController
public class BedController {

  @Autowired
  BedService bedService;

  public void setBedService(BedService bedService) {
    this.bedService = bedService;
  }

  @PostMapping(value = "/bed")
  public ResponseEntity<Bed> addBed(){
    final Bed bed = new Bed(true);
    final Bed newBed = bedService.addNewBed(bed);
    final HttpHeaders headers = new HttpHeaders();
    headers.setLocation(URI.create("/bed/" + newBed.getBedId()));
    return new ResponseEntity<>(headers, HttpStatus.CREATED);
  }

  @GetMapping(value = "/bed")
  public ResponseEntity<List<Bed>> getAllBeds(){
    final List<Bed> beds = bedService.getAllBeds();
    if (beds == null) {
      return new ResponseEntity<>(new ArrayList<Bed>(), HttpStatus.OK);
    }
    return new ResponseEntity<>(beds, HttpStatus.OK);
  }

  @GetMapping(value = "/bed/{id}")
  public ResponseEntity<Bed> getBedById(@PathVariable("id") int id){

    final Bed bed = bedService.findBed(id);

    if (bed != null){
      return new ResponseEntity<>(bed, HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping(value = "/bed/{id}")
  public ResponseEntity<Bed> deleteBed(@PathVariable("id") int id) {
    final Bed bed = bedService.findBed(id);
    if (bed != null){
      bedService.deleteExistingBed(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    else{
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }
}