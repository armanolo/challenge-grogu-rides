package org.mmm.challengegrogurides.infrastructure.persistence;

import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.repository.RentRepository;
import org.mmm.challengegrogurides.domain.valueobject.AvailableSeats;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;
import org.mmm.challengegrogurides.infrastructure.mapper.InfraRentMapper;
import org.mmm.challengegrogurides.infrastructure.persistence.model.RentDB;
import org.mmm.challengegrogurides.infrastructure.persistence.repositories.JpaRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class RentRepositoryCache implements RentRepository {

    private final JpaRentRepository jpaRentRepository;

    private final InfraRentMapper infraRentMapper;

    @Autowired
    public RentRepositoryCache(
            JpaRentRepository jpaRentRepository, InfraRentMapper infraRentMapper) {
        this.jpaRentRepository = jpaRentRepository;
        this.infraRentMapper = infraRentMapper;
    }

    @Override
    public void deleteAll() {
        this.jpaRentRepository.deleteAll();
    }

    @Override
    public void createRent(RentVehicle rentVehicle) {
        this.updateRent(rentVehicle);
    }

    @Override
    public Optional<VehicleId> getNotRentedVehicleIdOrderedByAvailableSeats(AvailableSeats seats) {
        UUID vehicleDBId = jpaRentRepository.getNotRentVehicleIdOrderedByAvailableSeats(seats.value());
        try {
            VehicleId vehicleId = new VehicleId(vehicleDBId.toString());
            return Optional.of(vehicleId);
        }catch (Exception ignored){}
        return Optional.empty();
    }

    @Override
    public List<RentVehicle> getRentList() {
        return jpaRentRepository.findAll().stream().map(infraRentMapper::entityToDomain).toList();
    }

    @Override
    public void updateRent(RentVehicle currentRent) {
        jpaRentRepository.save(infraRentMapper.domainToEntity(currentRent));
    }

    @Override
    public Optional<RentVehicle> getRentById(RentVehicleId rentVehicleId) {
        RentDB rentDB = jpaRentRepository.getReferenceById(rentVehicleId.uuid());
        return Optional.of(infraRentMapper.entityToDomain(rentDB));
    }
}
