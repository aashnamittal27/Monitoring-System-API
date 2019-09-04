/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class NursingStation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int stationId;

  @Column(name = "station_name")
  String name;

  int level; // eg level 3 icu, level 4 icu defines the monitoring expertise of icu
  // also coronary icu, pulmonary icu, stroke icu

  int bedCapacity;

  @JsonIgnore
  @OneToMany(mappedBy = "station")
  List<Bed> beds = new ArrayList<>(); // the cascade for deleting icu should delete all beds in it but cascade is
                                      // unreliable so use another way

  public NursingStation() {
  }
  public NursingStation(String name, int level, int bedCapacity) {
    super();
    this.name = name;
    this.level = level;
    this.bedCapacity = bedCapacity;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getLevel() {
    return level;
  }

  public void setLevel(int level) {
    this.level = level;
  }

  public int getStationId() {
    return stationId;
  }

  public void setStationId(int stationId) {
    this.stationId = stationId;
  }

  public List<Bed> getBeds() {
    return beds;
  }

  public void setBeds(List<Bed> beds) {
    this.beds = beds;
  }

  public int getBedCapacity() {
    return bedCapacity;
  }

  public void setBedCapacity(int bedCapacity) {
    this.bedCapacity = bedCapacity;
  }

  public void removeBed(Bed bed) {
    beds.remove(bed);
  }

  @Override
  public String toString() {
    return "NursingStation [bedCapacity=" + bedCapacity + ", level=" + level + ", name=" + name + ", stationId="
        + stationId + "]";
  }

}
