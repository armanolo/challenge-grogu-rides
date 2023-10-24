package org.mmm.challengegrogurides.application.service;

import org.mmm.challengegrogurides.application.service.mapper.VehicleMapper;
import org.mmm.challengegrogurides.domain.service.SetVehicleFleet;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SetVehicleFleetService {

    private final SetVehicleFleet setVehicleFleet;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public SetVehicleFleetService(SetVehicleFleet setVehicleFleet, VehicleMapper vehicleMapper) {
        this.setVehicleFleet = setVehicleFleet;
        this.vehicleMapper = vehicleMapper;
    }

    public void execute(List<VehicleDto> vehicleDtoList){
        setVehicleFleet.execute(
                vehicleMapper.dtosToDomains(vehicleDtoList)
        );
    }
}
