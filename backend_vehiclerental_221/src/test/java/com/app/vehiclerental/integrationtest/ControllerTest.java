package com.app.vehiclerental.integrationtest;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.app.vehiclerental.SpringApp;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.element.Node;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
class ControllerTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private final ObjectMapper mapper = new ObjectMapper();

  @Autowired
  private WebApplicationContext context;
  @LocalServerPort
  private int port;

  @BeforeEach
  void setup() {
    RestAssuredMockMvc.webAppContextSetup(context);
  }

  
  
   private JsonNode getJSONFromFile(String filePath) throws IOException {
    try(InputStream in=Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath)){
      JsonNode jsonNode = mapper.readValue(in, JsonNode.class);
      return jsonNode;
    }
    catch(Exception e){
      throw new RuntimeException(e);
    }
  }
  
  private String getPayload(String filePath) throws IOException {
	  String jsonString = mapper.writeValueAsString( getJSONFromFile(filePath) );
	  return jsonString;
  }

  @Test
  void testRetrieveServiceDocument() {
    final String xml = given()
        .accept(ContentType.XML)
        .when()
        .get("/vehiclerental/")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Collection<Node> n = ((Node) ((Node) path.get("service")).get("workspace")).get("collection");
    assertNotNull(n);
    assertFalse(n.isEmpty());
  }

  @Test
  void  testRetrieveMetadataDocument() {
    final String xml = given()
        .when()
        .get("/vehiclerental/$metadata")
        .then()
        .statusCode(HttpStatusCode.OK.getStatusCode())
        .contentType(ContentType.XML)
        .extract()
        .asString();

    final XmlPath path = new XmlPath(xml);
    final Node n = ((Node) ((Node) path.get("edmx:Ed mx")).get("DataServices")).get("Schema");
    assertNotNull(n);
    assertEquals("vehiclerental", n.getAttribute("Namespace"));
    assertNotNull(n.get("EntityContainer"));
  }

	

	
  @Test
  void  testCreateVerficationInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("VerficationInstance.json"))
        .when()
        .post("/vehiclerental/Verfications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsVerfication() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("VerficationInstance.json"))
        .when()
        .post("/vehiclerental/Verfications")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/Verfications?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).VerificationID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/Verfications/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreatePaymentInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("PaymentInstance.json"))
        .when()
        .post("/vehiclerental/Payments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsPayment() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("PaymentInstance.json"))
        .when()
        .post("/vehiclerental/Payments")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/Payments?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).PaymentID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/Payments/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateCustomerInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("CustomerInstance.json"))
        .when()
        .post("/vehiclerental/Customers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsCustomer() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("CustomerInstance.json"))
        .when()
        .post("/vehiclerental/Customers")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/Customers?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CustomerID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/Customers/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateInsuranceInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("InsuranceInstance.json"))
        .when()
        .post("/vehiclerental/Insurances")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsInsurance() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("InsuranceInstance.json"))
        .when()
        .post("/vehiclerental/Insurances")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/Insurances?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).InsuranceID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/Insurances/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateServiceCrewInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ServiceCrewInstance.json"))
        .when()
        .post("/vehiclerental/ServiceCrews")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsServiceCrew() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ServiceCrewInstance.json"))
        .when()
        .post("/vehiclerental/ServiceCrews")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/ServiceCrews?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).ServiceID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/ServiceCrews/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateReturnRentedBikeInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ReturnRentedBikeInstance.json"))
        .when()
        .post("/vehiclerental/ReturnRentedBikes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsReturnRentedBike() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ReturnRentedBikeInstance.json"))
        .when()
        .post("/vehiclerental/ReturnRentedBikes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/ReturnRentedBikes?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BookingID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/ReturnRentedBikes/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateBookingInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("BookingInstance.json"))
        .when()
        .post("/vehiclerental/Bookings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsBooking() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("BookingInstance.json"))
        .when()
        .post("/vehiclerental/Bookings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/Bookings?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BookingID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/Bookings/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateRoadsideAssistanceInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("RoadsideAssistanceInstance.json"))
        .when()
        .post("/vehiclerental/RoadsideAssistances")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsRoadsideAssistance() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("RoadsideAssistanceInstance.json"))
        .when()
        .post("/vehiclerental/RoadsideAssistances")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/RoadsideAssistances?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CustomerID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/RoadsideAssistances/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateExtendBookingInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ExtendBookingInstance.json"))
        .when()
        .post("/vehiclerental/ExtendBookings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsExtendBooking() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ExtendBookingInstance.json"))
        .when()
        .post("/vehiclerental/ExtendBookings")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/ExtendBookings?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BookingID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/ExtendBookings/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateRentalCompanyInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("RentalCompanyInstance.json"))
        .when()
        .post("/vehiclerental/RentalCompanies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsRentalCompany() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("RentalCompanyInstance.json"))
        .when()
        .post("/vehiclerental/RentalCompanies")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/RentalCompanies?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).CompanyID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/RentalCompanies/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateReturnBikeInspectionInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("ReturnBikeInspectionInstance.json"))
        .when()
        .post("/vehiclerental/ReturnBikeInspections")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsReturnBikeInspection() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("ReturnBikeInspectionInstance.json"))
        .when()
        .post("/vehiclerental/ReturnBikeInspections")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/ReturnBikeInspections?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BikeID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/ReturnBikeInspections/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
	

	
  @Test
  void  testCreateBikeInstance() throws IOException {
    given()
        .contentType("application/json")
        .body(getPayload("BikeInstance.json"))
        .when()
        .post("/vehiclerental/Bikes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
    
  }
	
	
  
   
  
   @Test
  public void testSystemFilterOptionsBike() throws IOException {
  
  given()
        .contentType("application/json")
        .body(getPayload("BikeInstance.json"))
        .when()
        .post("/vehiclerental/Bikes")
        .then()
        .statusCode(HttpStatusCode.CREATED.getStatusCode());
   given()
            .when()
            .get("/vehiclerental/Bikes?$top=1")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body("value.get(0).BikeID", equalTo("<<replace_with_keyFieldValue>>"))
            .body("value.size()", is(1));
    given()
            .when()
            .get("/vehiclerental/Bikes/$count")
            .then()
            .statusCode(HttpStatusCode.fromStatusCode(200).getStatusCode())
            .body(is("1"));
            
            
    
    } 
	
           
       
  
  
  
  
 
  @AfterEach
  void  teardown() {
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.Verfication");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.Payment");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.Customer");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.Insurance");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.ServiceCrew");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.ReturnRentedBike");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.Booking");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.RoadsideAssistance");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.ExtendBooking");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.RentalCompany");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.ReturnBikeInspection");
    jdbcTemplate.execute("DELETE FROM vehiclerental_070.Bike");
     jdbcTemplate.execute("DELETE FROM vehiclerental_070.ReturnRentedBikeInspection");
     jdbcTemplate.execute("DELETE FROM vehiclerental_070.CustomerExtendRental");
     jdbcTemplate.execute("DELETE FROM vehiclerental_070.RentalCompanyBookings");
     jdbcTemplate.execute("DELETE FROM vehiclerental_070.RentalCompanyCustomers");
     jdbcTemplate.execute("DELETE FROM vehiclerental_070.CustomerBookings");
     jdbcTemplate.execute("DELETE FROM vehiclerental_070.RentalCompanyBikes");
     jdbcTemplate.execute("DELETE FROM vehiclerental_070.CustomerReturns");

    RestAssuredMockMvc.reset();
  }
}
