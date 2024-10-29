package com.app.vehiclerental.function;

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
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmFunction;
import com.sap.olingo.jpa.metadata.core.edm.annotation.EdmParameter;
import com.sap.olingo.jpa.metadata.core.edm.mapper.extension.ODataFunction;
import com.app.vehiclerental.repository.VerficationRepository;
import com.app.vehiclerental.repository.PaymentRepository;
import com.app.vehiclerental.repository.CustomerRepository;
import com.app.vehiclerental.repository.InsuranceRepository;
import com.app.vehiclerental.repository.ServiceCrewRepository;
import com.app.vehiclerental.repository.ReturnRentedBikeRepository;
import com.app.vehiclerental.repository.BookingRepository;
import com.app.vehiclerental.repository.RoadsideAssistanceRepository;
import com.app.vehiclerental.repository.ExtendBookingRepository;
import com.app.vehiclerental.repository.RentalCompanyRepository;
import com.app.vehiclerental.repository.ReturnBikeInspectionRepository;
import com.app.vehiclerental.repository.BikeRepository;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class JavaFunctions implements ODataFunction {


    
    
}
   
