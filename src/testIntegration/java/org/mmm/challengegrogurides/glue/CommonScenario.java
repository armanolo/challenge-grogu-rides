package org.mmm.challengegrogurides.glue;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.cucumber.datatable.DataTable;
import org.mmm.challengegrogurides.infrastructure.persistence.model.UserDB;
import org.mmm.challengegrogurides.infrastructure.persistence.model.VehicleDB;
import org.mmm.challengegrogurides.infrastructure.persistence.repositories.JpaUserRepository;
import org.mmm.challengegrogurides.infrastructure.persistence.repositories.JpaVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.ext.ScriptUtils;
import org.testcontainers.jdbc.JdbcDatabaseDelegate;
import org.testcontainers.junit.jupiter.Container;

import javax.sql.DataSource;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("bddtest")
@Sql(
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
        scripts = "classpath:/sql/clean_db.sql"
)
public abstract class CommonScenario {
    private static final String IMAGE_VERSION = "postgres:latest";
    protected static final ObjectMapper objectMapper;
    static {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Container
    private static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(IMAGE_VERSION)
            .withDatabaseName("grogu")
            .withUsername("grogu")
            .withPassword("grogu");
    private static JdbcDatabaseDelegate containerDelegate;
    @Autowired
    protected CucumberContextHolder cucumberContext;
    protected ResponseEntity<String> responseEntity;
    @Autowired
    protected TestRestTemplate restTemplate;
    @Autowired
    protected JpaVehicleRepository jpaVehicleRepository;
    @Autowired
    protected JpaUserRepository jpaUserRepository;

    /**
     * Initial database setup
     */
    protected static void parentSetup() {
        if (!postgresContainer.isRunning()){
            postgresContainer.start();
            containerDelegate = new JdbcDatabaseDelegate(postgresContainer, "");
        }
    }

    @DynamicPropertySource
    static void postgresqlProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", CommonScenario::getJDBCUrlWithString);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    static String getJDBCUrlWithString() {
        return postgresContainer.getJdbcUrl() + "&stringtype=unspecified";
    }

    protected void executeScript(String path) {
        ScriptUtils.runInitScript(containerDelegate, path);
    }

    /**
     * Datasource dynamic configuration
     *
     */
    @TestConfiguration
    static class PostgresTestConfiguration {
        @Bean
        DataSource dataSource() {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl(postgresContainer.getJdbcUrl());
            hikariConfig.setUsername(postgresContainer.getUsername());
            hikariConfig.setPassword(postgresContainer.getPassword());
            return new HikariDataSource(hikariConfig);
        }
    }

    /**
     * Shutdown
     */
    public static void parentTearDown() {
        //do nothing, JVM handles shut down
    }

    public void receive_a_correct_response_entity() {
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(200);
    }

    public void receive_a_correct_created_response_entity() {
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);
    }

    public void receive_a_correct_response_cucumber() {
        assertThat(cucumberContext.getResponse().getStatusCode()).isEqualTo(200);
    }

    public void i_receive_a_bad_response_cucumber() {
        assertThat(cucumberContext.getResponse().getStatusCode()).isEqualTo(400);
    }

    public void receive_a_not_found_response_entity(){
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(404);
    }

    public void i_receive_a_bad_response_entity() {
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(400);
    }

    public void set_list_of_vehicles(DataTable dataVehicleList){
        List<List<String>> dataRows = dataVehicleList.asLists();
        List<VehicleDB> userDBList = dataRows.stream().map(
                dataRow -> new VehicleDB(UUID.fromString(dataRow.get(0)), Integer.valueOf(dataRow.get(1)))
        ).toList();
        jpaVehicleRepository.saveAll(userDBList);
    }

    public void set_user_list(DataTable dataUserList){
        List<List<String>> dataRows = dataUserList.asLists();
        List<UserDB> userDBList = dataRows.stream().map(
                dataRow -> new UserDB(
                        UUID.fromString(dataRow.get(0)), dataRow.get(1), dataRow.get(2))
        ).toList();
        jpaUserRepository.saveAll(userDBList);
    }
}
