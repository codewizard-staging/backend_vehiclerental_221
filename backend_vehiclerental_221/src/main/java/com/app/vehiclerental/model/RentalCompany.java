package com.app.vehiclerental.model;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;


 
import com.app.vehiclerental.model.RoadsideAssistance;
import com.app.vehiclerental.model.Verfication;
import com.app.vehiclerental.model.Payment;
import com.app.vehiclerental.model.ServiceCrew;
import com.app.vehiclerental.model.Insurance;
import com.app.vehiclerental.model.ExtendBooking;
import com.app.vehiclerental.model.Booking;
import com.app.vehiclerental.model.RentalCompany;
import com.app.vehiclerental.model.ReturnBikeInspection;
import com.app.vehiclerental.model.Customer;
import com.app.vehiclerental.model.Bike;
import com.app.vehiclerental.model.ReturnRentedBike;
import com.app.vehiclerental.enums.IDTypes;
import com.app.vehiclerental.converter.IDTypesConverter;
import com.app.vehiclerental.converter.DurationConverter;
import com.app.vehiclerental.converter.UUIDToByteConverter;
import com.app.vehiclerental.converter.UUIDToStringConverter;

import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Duration;
import java.util.Date;
import java.sql.Timestamp;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Lob;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmMediaStream;

@Entity(name = "RentalCompany")
@Table(name = "\"RentalCompany\"", schema =  "\"vehiclerental_070\"")
@Data
                        
public class RentalCompany {
	public RentalCompany () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"CompanyID\"", nullable = true )
  private Integer companyID;
	  
  @Column(name = "\"CompanyName\"", nullable = true )
  private String companyName;
  
	  
  @Column(name = "\"CompanyRegistrationNo\"", nullable = true )
  private String companyRegistrationNo;
  
  
  
  
   
	
@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"RentalCompanyBikes\"",
            joinColumns = @JoinColumn( name="\"CompanyID\""),
            inverseJoinColumns = @JoinColumn( name="\"BikeID\""), schema = "\"vehiclerental_070\"")
private List<Bike> bikes = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"RentalCompanyCustomers\"",
            joinColumns = @JoinColumn( name="\"CompanyID\""),
            inverseJoinColumns = @JoinColumn( name="\"CustomerID\""), schema = "\"vehiclerental_070\"")
private List<Customer> customers = new ArrayList<>();


@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
@JoinTable(
            name="\"RentalCompanyBookings\"",
            joinColumns = @JoinColumn( name="\"CompanyID\""),
            inverseJoinColumns = @JoinColumn( name="\"BookingID\""), schema = "\"vehiclerental_070\"")
private List<Booking> bookings = new ArrayList<>();
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "RentalCompany [" 
  + "CompanyID= " + companyID  + ", " 
  + "CompanyName= " + companyName  + ", " 
  + "CompanyRegistrationNo= " + companyRegistrationNo 
 + "]";
	}
	
}