package org.mmm.challengegrogurides.infrastructure.persistence;

import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.repository.VehicleRepository;
import org.mmm.challengegrogurides.infrastructure.mapper.InfraVehicleMapper;
import org.mmm.challengegrogurides.infrastructure.persistence.repositories.JpaVehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VehicleRepositoryCache implements VehicleRepository {

    private final JpaVehicleRepository jpaVehicleRepository;
    private final InfraVehicleMapper vehicleMapper;

    @Autowired
    public VehicleRepositoryCache(
            JpaVehicleRepository jpaVehicleRepository,
            InfraVehicleMapper vehicleMapper) {
        this.jpaVehicleRepository = jpaVehicleRepository;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public void setVehicles(List<Vehicle> vehicleList) {
        jpaVehicleRepository.saveAll(
                vehicleMapper.domainsToEntities(vehicleList)
        );
    }

    @Override
    public void deleteVehicles() {
        jpaVehicleRepository.deleteAll();
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleMapper.entitiesToDomains(jpaVehicleRepository.findAll());
    }
}
