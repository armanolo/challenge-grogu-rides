package org.mmm.challengegrogurides.application.service;

import org.mmm.challengegrogurides.application.service.mapper.VehicleMapper;
import org.mmm.challengegrogurides.domain.service.GetVehicleFleet;
import org.mmm.challengegrogurides.infrastructure.controller.filter.VehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetVehicleFleetService {

    private final GetVehicleFleet getVehicleFleet;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public GetVehicleFleetService(GetVehicleFleet getVehicleFleet, VehicleMapper vehicleMapper) {
        this.getVehicleFleet = getVehicleFleet;
        this.vehicleMapper = vehicleMapper;
    }

    public List<VehicleDto> execute(){
        return vehicleMapper.domainsToDTOs(getVehicleFleet.execute());
    }
}
