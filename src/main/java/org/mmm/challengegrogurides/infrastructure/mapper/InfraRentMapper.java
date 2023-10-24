package org.mmm.challengegrogurides.infrastructure.mapper;

import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.valueobject.EndRentTime;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;
import org.mmm.challengegrogurides.domain.valueobject.ReturnTime;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;
import org.mmm.challengegrogurides.infrastructure.persistence.model.RentDB;
import org.mmm.challengegrogurides.infrastructure.persistence.model.UserDB;
import org.mmm.challengegrogurides.infrastructure.persistence.model.VehicleDB;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class InfraRentMapper {

    public RentVehicle entityToDomain(RentDB rentDB){
        RentVehicleId rentVehicleId = new RentVehicleId(rentDB.getId().toString());
        VehicleId vehicleId = new VehicleId(rentDB.getVehicle().getId().toString());
        UserId userId = new UserId(rentDB.getUser().getId().toString());
        ReturnTime returnTime = null;
        LocalDateTime localReturnTime = rentDB.getReturnTime();
        if (localReturnTime != null){
            returnTime = new ReturnTime(localReturnTime);
        }
        return new RentVehicle(rentVehicleId,vehicleId, userId, new EndRentTime(rentDB.getEndTime()), returnTime);
    }

    public RentDB domainToEntity(RentVehicle rentVehicle){
        VehicleDB vehicleDB = new VehicleDB();
        vehicleDB.setId(rentVehicle.vehicleId().uuid());
        UserDB userDB = new UserDB();
        userDB.setId(rentVehicle.userId().uuid());
        LocalDateTime returnTime = rentVehicle.returnTime() == null ? null : rentVehicle.returnTime().value();

        return new RentDB(rentVehicle.id().uuid(), userDB, vehicleDB, LocalDateTime.now(),
                rentVehicle.endRentTime().value(), returnTime);
    }
}
