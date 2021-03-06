/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Bed {// links to icu(1->Many) in rel //links to pt in 1-1 rel

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int bedId;

  @Column(name = "availability")
  boolean isAvailable;

  // @ManyToOne
  // @JoinColumn(name = "station_id")
  // NursingStation station;

  @JsonIgnore
  @OneToOne(mappedBy = "bed")
  Patient patient;

  public Bed() {
  }
  
  public Bed( boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  public int getBedId() {
    return bedId;
  }

  public void setBedId(int bedId) {
    this.bedId = bedId;
  }

  public Patient getPatient() {
    return patient;
  }

  public void setPatient(Patient patient) {
    this.patient = patient;
    setisAvailable();
  }

  public boolean getisAvailable() {
    return isAvailable;
  }

  // sets the occupancy status using the relation of bed with patient
  public void setisAvailable() {
    this.isAvailable = (this.getPatient() == null) ? true : false;
  }

  // public NursingStation getStation() {
  //   return station;
  // }

  // public void setStation(NursingStation station) {
  //   this.station = station;
  // }

  @Override
  public String toString() {
    return "Bed [bedId=" + bedId + ", isAvailable=" + isAvailable + ", patient=" + patient + "]";
  }
}
