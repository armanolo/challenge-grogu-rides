package org.mmm.challengegrogurides.glue.rent;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.ContentType;
import org.mmm.challengegrogurides.glue.CommonScenario;
import org.mmm.challengegrogurides.infrastructure.controller.filter.OrderRentDto;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.mmm.challengegrogurides.infrastructure.persistence.model.RentDB;
import org.mmm.challengegrogurides.infrastructure.persistence.repositories.JpaRentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
public class RentScenario extends CommonScenario{
    private static final String RENT_ENDPOINT = "http://localhost:8080/rent";
    @Autowired
    protected JpaRentRepository jpaRentRepository;
    @BeforeAll
    public static void setup(){
        parentSetup();
    }

    @AfterAll
    public static void tearDown(){
        parentTearDown();
    }

    //GIVEN
    @Given("the following users exist in DB: rent")
    public void set_user_list_rent(DataTable dataUserList){
        set_user_list(dataUserList);
    }

    @Given("a list of rent in database")
    public void set_list_of_order_rent(){
        executeScript("sql/rent/rentList.sql");
    }

    @Given("a rent in database")
    public void set_an_order_rent(){
        executeScript("sql/rent/rentOneVehicle.sql");
    }
    @Given("a list of rented and returned in database")
    public void set_list_of_order_rented_and_returned(){
        executeScript("sql/rent/allRentedList.sql");
    }

    @And("the following vehicles exist in DB: rent")
    public void set_list_of_vehicles_rent(DataTable dataVehicleList){
        set_list_of_vehicles(dataVehicleList);
    }


    //WHEN
    @When("I submit to rent user id {string} during {int} hours with {int} seats")
    public void submit_rent_a_vehicle(String userId, int hours, int seats) throws JsonProcessingException {
        LocalDateTime localDateTime = LocalDateTime.now().plus(hours, ChronoUnit.HOURS);
        OrderRentDto orderRentDto = new OrderRentDto(userId, localDateTime, seats);

        String requestJson = objectMapper.writeValueAsString(orderRentDto);
        cucumberContext.setResponse(given().body(requestJson).contentType(ContentType.JSON)
                .accept(ContentType.JSON).when().post(RENT_ENDPOINT));
    }

    @When("request a list of rents")
    public void request_list_of_rents(){
        cucumberContext.setResponse(given().when().get(RENT_ENDPOINT));
    }

    @When("I submit to drop off for the rent vehicle with id {string}")
    public void submit_drop_off_rented_vehicle_with_id(String rentVehicleId){
        String dropOffEndPoint = String.format("%s/%s/drop-off",RENT_ENDPOINT,rentVehicleId);
        cucumberContext.setResponse(given().when().post(dropOffEndPoint));
    }

    //THEN
    @Then("I receive a correct response: rent")
    public void i_receive_a_correct_response_rent(){
        receive_a_correct_response_cucumber();
    }

    @Then("I receive a correct accepted response but not done: rent")
    public void i_receive_a_correct_accepted_response_but_not_rented(){
        assertThat(cucumberContext.getResponse().getStatusCode()).isEqualTo(202);
    }

    @And("verify that vehicle rented has id {string} by user id {string}")
    public void verify_vehicle_rented_by_id(String vehicleId, String userId){
        RentVehicleDto rentVehicleDto = cucumberContext.getResponse().body().as(RentVehicleDto.class);
        assertThat(vehicleId).isEqualTo(rentVehicleDto.vehicleId());
        assertThat(userId).isEqualTo(rentVehicleDto.userId());
    }

    @And("verify the list of rents")
    public void verify_list_of_rents(){
        List<RentVehicleDto> rentVehicleDtoList = List.of(cucumberContext.getResponse().body()
                .as(RentVehicleDto[].class));
        assertThat(rentVehicleDtoList.size()).isSameAs(2);
    }

    @And("verify that rented vehicle with id {string} has been dropped off")
    public void verify_rented_vehicle_with_id(String rentVehicleId) {
        Optional<RentDB> rentVehicleDB = jpaRentRepository.findById(UUID.fromString(rentVehicleId));

        assertThat(rentVehicleDB.isEmpty()).isFalse();
        rentVehicleDB.ifPresent(
                rentVehicle -> {
                    LocalDateTime returnTimeOff = rentVehicle.getReturnTime();
                    LocalDateTime startTime = rentVehicle.getStartTime();
                    Duration d = Duration.between(startTime, returnTimeOff);
                    //int time = (int) d.toHours();
                    //assertThat(time).isSameAs(2);
                    int intDe = (d.getNano()/100000000);
                    assertThat(intDe).isGreaterThan(1);
                }
        );
    }

    @And("verify that {int} vehicles has been rented")
    public void verify_number_of_vehicles_have_been_rented(Integer vehiclesRented) {
        long numberOfVehicleRented = jpaRentRepository.count();
        long expected = vehiclesRented;

        assertThat(numberOfVehicleRented).isSameAs(expected);
    }
}
