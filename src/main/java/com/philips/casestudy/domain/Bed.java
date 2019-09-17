/*
 * The copyright of this file belongs to Koninklijke Philips N.V., 2019.
 */
package com.philips.casestudy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bed {// links to icu(1->Many) in rel //links to pt in 1-1 rel

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int bedId;

  @Column(name = "availability")
  boolean isAvailable;

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

  public boolean getisAvailable() {
    return isAvailable;
  }

  // sets the occupancy status using the relation of bed with patient
  public void setisAvailable(boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  @Override
  public String toString() {
    return "Bed [bedId=" + bedId + ", isAvailable=" + isAvailable + "]";
  }
}
