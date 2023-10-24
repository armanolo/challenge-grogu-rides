package org.mmm.challengegrogurides.infrastructure.mapper;

import org.mmm.challengegrogurides.domain.Vehicle;
import org.mmm.challengegrogurides.domain.valueobject.AvailableSeats;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;
import org.mmm.challengegrogurides.infrastructure.persistence.model.VehicleDB;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class InfraVehicleMapper {

    public Vehicle entityToDomain(VehicleDB vehicleDB){
        return new Vehicle(new VehicleId(vehicleDB.getId().toString()),new AvailableSeats(vehicleDB.getSeats()));
    }

    public VehicleDB domainToEntity(Vehicle vehicle) {
        return new VehicleDB(vehicle.id().uuid(), vehicle.availableSeats().value());
    }

    public List<Vehicle> entitiesToDomains(List<VehicleDB> vehicleDBList) {
        return vehicleDBList.stream().map(this::entityToDomain).toList();
    }

    public List<VehicleDB> domainsToEntities(List<Vehicle> vehicleList) {
        return vehicleList.stream().map(this::domainToEntity).toList();
    }
}
