package org.mmm.challengegrogurides.glue.user;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.AfterAll;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.mmm.challengegrogurides.glue.CommonScenario;
import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import static org.assertj.core.api.Assertions.assertThat;

@CucumberContextConfiguration
public class UserScenario extends CommonScenario{

    private static final String USER_ENDPOINT = "/user";
    private UserDto userDto = null;

    @BeforeAll
    public static void setup(){
        parentSetup();
    }

    @AfterAll
    public static void tearDown(){
        parentTearDown();
    }


    // GIVEN
    @Given("user with uuid {string}, dni {string} and name {string}")
    public void user_id_and_name(String uuid, String dni, String name) {
        userDto = new UserDto(uuid, dni, name);
    }

    @Given("the following users exist in DB: user")
    public void set_user_list_user(DataTable dataUserList){
        set_user_list(dataUserList);
    }

    // WHEN
    @When("I submit this information to save a new user")
    public void i_submit_user_information_to_save() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(
                objectMapper.writeValueAsString(userDto),
                headers
        );

        responseEntity = restTemplate.exchange(USER_ENDPOINT,
                HttpMethod.POST,
                request,
                String.class);
    }

    // WHEN
    @When("I request info for the user with id {string}")
    public void request_info_for_a_user_id(String uuid) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);

        String pathToRequest = String.format("%s/%s",USER_ENDPOINT, uuid);
        responseEntity = restTemplate.exchange(pathToRequest,
                HttpMethod.GET,
                request,
                String.class);
    }

    // THEN
    @Then("I receive a correct response: user")
    public void i_receive_a_correct_response_user() {
        receive_a_correct_response_entity();
    }

    @Then("I receive a correct created response: user")
    public void i_receive_a_correct_created_response_user() {
        receive_a_correct_created_response_entity();
    }

    @Then("I receive a no found: user")
    public void i_receive_a_not_found_user() {
        receive_a_not_found_response_entity();
    }

    @Then("I receive a bad response: user")
    public void i_receive_a_bad_response_vehicle() {
        i_receive_a_bad_response_entity();
    }

    @And("verify a user with id {string} dni {string} and name {string}")
    public void verify_list_of_users(String uuid, String dni, String name) throws JsonProcessingException {
        UserDto userDtoExpected = new UserDto(uuid, dni, name);
        UserDto userDtoFromRequest = objectMapper.readValue(responseEntity.getBody(), UserDto.class);

        assertThat(userDtoExpected).isEqualTo(userDtoFromRequest);
    }
}
