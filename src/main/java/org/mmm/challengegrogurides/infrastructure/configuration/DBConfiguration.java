package org.mmm.challengegrogurides.infrastructure.configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableJpaRepositories(basePackageClasses = JpaVehicleRepository.class)
@EnableJpaRepositories(basePackages = "org.mmm.challengegrogurides.infrastructure.persistence.repositories")
public class DBConfiguration {
}
