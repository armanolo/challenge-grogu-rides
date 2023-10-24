package org.mmm.challengegrogurides.application.service;

import org.mmm.challengegrogurides.application.service.mapper.RentMapper;
import org.mmm.challengegrogurides.domain.RentVehicle;
import org.mmm.challengegrogurides.domain.service.OrderRentVehicle;
import org.mmm.challengegrogurides.infrastructure.controller.filter.OrderRentDto;
import org.mmm.challengegrogurides.infrastructure.controller.filter.RentVehicleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderRentVehicleService {

    private final OrderRentVehicle orderRentVehicle;
    private final RentMapper rentMapper;

    @Autowired
    public OrderRentVehicleService(OrderRentVehicle orderRentVehicle, RentMapper rentMapper) {
        this.orderRentVehicle = orderRentVehicle;
        this.rentMapper = rentMapper;
    }

    public RentVehicleDto execute(OrderRentDto orderRentDto){
        RentVehicle rentVehicle = orderRentVehicle.execute(
                rentMapper.dtoToDomain(orderRentDto)
        );
        if (rentVehicle == null){
            return null;
        }
        return rentMapper.domainToDto(rentVehicle);
    }
}
