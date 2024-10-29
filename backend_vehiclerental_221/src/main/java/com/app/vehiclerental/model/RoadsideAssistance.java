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

@Entity(name = "RoadsideAssistance")
@Table(name = "\"RoadsideAssistance\"", schema =  "\"vehiclerental_070\"")
@Data
                        
public class RoadsideAssistance {
	public RoadsideAssistance () {   
  }
	  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "\"CustomerID\"", nullable = true )
  private Integer customerID;
	  
  @Column(name = "\"BookingID\"", nullable = true )
  private Integer bookingID;
  
	  
  @Column(name = "\"BikeID\"", nullable = true )
  private Integer bikeID;
  
	  
  @Column(name = "\"Manufacturer\"", nullable = true )
  private String manufacturer;
  
	  
  @Column(name = "\"BikeModel\"", nullable = true )
  private String bikeModel;
  
	  
  @Column(name = "\"ProblemwithBike\"", nullable = true )
  private Boolean problemwithBike;
  
	  
  @Column(name = "\"Address\"", nullable = true )
  private String address;
  
  
  
  
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "\"RoadsideAssistanceService\"", referencedColumnName = "\"ServiceID\"", insertable = false, updatable = false)
	private ServiceCrew service;
	
	@Column(name = "\"RoadsideAssistanceService\"")
	private Integer roadsideAssistanceService;
   
  
  
  
  
  
  
  
  
  
  @Override
  public String toString() {
	return "RoadsideAssistance [" 
  + "CustomerID= " + customerID  + ", " 
  + "BookingID= " + bookingID  + ", " 
  + "BikeID= " + bikeID  + ", " 
  + "Manufacturer= " + manufacturer  + ", " 
  + "BikeModel= " + bikeModel  + ", " 
  + "ProblemwithBike= " + problemwithBike  + ", " 
  + "Address= " + address 
 + "]";
	}
	
}