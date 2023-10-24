package org.mmm.challengegrogurides.application.service;

import org.mmm.challengegrogurides.application.service.mapper.RentMapper;
import org.mmm.challengegrogurides.domain.service.GetRentVehicle;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetRentVehicleService {

    private final GetRentVehicle getRentVehicle;
    private final RentMapper rentMapper;

    @Autowired
    public GetRentVehicleService(GetRentVehicle getRentVehicle, RentMapper rentMapper) {
        this.getRentVehicle = getRentVehicle;
        this.rentMapper = rentMapper;
    }

    public List<RentVehicleDto> execute(){
        return rentMapper.domainsToDTOs(getRentVehicle.execute());
    }
}
