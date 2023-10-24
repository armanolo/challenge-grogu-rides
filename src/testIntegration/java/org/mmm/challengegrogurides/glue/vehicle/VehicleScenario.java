package org.mmm.challengegrogurides.glue.vehicle;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.http.ContentType;
import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.glue.CommonScenario;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.mmm.challengegrogurides.infrastructure.mapper.InfraVehicleMapper;
import org.mmm.challengegrogurides.infrastructure.persistence.model.VehicleDB;
import org.springframework.beans.factory.annotation.Autowired;
import static io.restassured.RestAssured.given;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertFalse;
import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
public class VehicleScenario extends CommonScenario{
    private final static String VEHICLES_ENDPOINT = "http://localhost:8080/vehicles";
    @Autowired
    private InfraVehicleMapper infraVehicleMapper;
    private VehicleDto vehicleDto = null;

    @BeforeAll
    public static void setup(){
        parentSetup();
    }

    @AfterAll
    public static void tearDown(){
        parentTearDown();
    }

    //GIVEN
    @Given("vehicle with uuid {string} with seats {int}")
    public void vehicle_id_and_seats(String uuid, int seats) {
        vehicleDto = new VehicleDto(uuid, seats);
    }

    @Given("a list of vehicle in database")
    public void set_list_of_vehicles() {
        executeScript("sql/vehicle/vehicleList.sql");
    }

    @And("the following vehicles exist in DB: vehicle")
    public void set_list_of_vehicles_vehicle(DataTable dataVehicleList){
        set_list_of_vehicles(dataVehicleList);
    }

    //WHEN
    @When("I submit this information to save a new vehicle")
    public void i_submit_this_information_to_save_a_vehicle() throws JsonProcessingException {
        List<VehicleDto> vehicleDtoList = List.of(vehicleDto);
        String requestJson = objectMapper.writeValueAsString(vehicleDtoList);

        cucumberContext.setResponse(given().body(requestJson).contentType(ContentType.JSON)
                .accept(ContentType.JSON).when().post(VEHICLES_ENDPOINT));

        vehicleDto = null;
    }

    @When("I submit this information to save a new vehicle try")
    public void i_submit_this_information_to_save_a_new_vehicle_try() throws JsonProcessingException {
        List<VehicleDto> vehicleDtoList = List.of(vehicleDto);
        String requestJson = objectMapper.writeValueAsString(vehicleDtoList);

        cucumberContext.setResponse(given().body(requestJson).contentType(ContentType.JSON)
                .accept(ContentType.JSON).when().post(VEHICLES_ENDPOINT));

        vehicleDto = null;
    }

    @When("I request a list of vehicles")
    public void request_list_of_vehicles() {
        cucumberContext.setResponse(when().get(VEHICLES_ENDPOINT));
    }


    //THEN
    @Then("I receive a correct response: vehicle")
    public void i_receive_a_correct_response_vehicle() {
        receive_a_correct_response_cucumber();
    }

    @Then("I receive a bad response: vehicle")
    public void i_receive_a_bad_response_vehicle() {
        i_receive_a_bad_response_cucumber();
    }

    @And("vehicle with uuid {string} with seats {int} is created in DB")
    public void a_uuid_with_seats_is_created_in_db(String strUuid, int seats) {
        UUID uuid = UUID.fromString(strUuid);
        Optional<VehicleDB> optionalVehicleDB = jpaVehicleRepository.findById(uuid);
        assertFalse(optionalVehicleDB.isEmpty());
        List<Vehicle> vehicles = infraVehicleMapper.entitiesToDomains(List.of(optionalVehicleDB.get()));
        Vehicle vehicle = vehicles.get(0);
        assertThat(seats).isEqualTo(vehicle.availableSeats().value());
    }

    @And("verify a list of vehicles")
    public void verify_list_of_vehicles() {
        List<VehicleDto> vehicleDtoList = List.of(cucumberContext.getResponse().body().as(VehicleDto[].class));
        assertThat(vehicleDtoList.size()).isSameAs(3);
        assertThat(200).isEqualTo(cucumberContext.getResponse().getStatusCode());
    }
}
