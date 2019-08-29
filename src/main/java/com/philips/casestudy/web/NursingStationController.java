package com.philips.casestudy.web;

import java.net.URI;
import java.util.List;

import com.philips.casestudy.domain.NursingStation;
import com.philips.casestudy.service.NursingStationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NursingStationController{

    @Autowired
    NursingStationService stationService;

    @RequestMapping(value = "/api/icu", method = RequestMethod.POST)
    public ResponseEntity<NursingStation> addStation(@RequestBody NursingStation station){
        System.out.println(stationService == null);
        try{
            int id = stationService.addNewStation(station);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/api/icu/"+id));
            return new ResponseEntity<>(headers, HttpStatus.CREATED);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/api/icu", method = RequestMethod.GET)
    public List<NursingStation> getAllStations(){
        return stationService.getAllStations();
    }

    @RequestMapping(value = "/api/icu/{id}", method = RequestMethod.GET)
    public ResponseEntity<NursingStation> getStationById(@PathVariable("id")int id){
        
        NursingStation station = stationService.findStation(id);

        if(station!=null){
            return new ResponseEntity<>(station, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/api/icu/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<NursingStation> deleteStation(@PathVariable("id") int id){

        NursingStation station = stationService.findStation(id);
        if(station!=null){
            stationService.deleteExistingStation(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}