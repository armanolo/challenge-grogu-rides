package org.mmm.challengegrogurides.infrastructure.configuration;

import org.mmm.challengegrogurides.TheChallengeGroguRidesApplication;
import org.mmm.challengegrogurides.domain.repository.RentRepository;
import org.mmm.challengegrogurides.domain.repository.UserRepository;
import org.mmm.challengegrogurides.domain.repository.VehicleRepository;
import org.mmm.challengegrogurides.domain.service.CreateUser;
import org.mmm.challengegrogurides.domain.service.DropOff;
import org.mmm.challengegrogurides.domain.service.GetRentVehicle;
import org.mmm.challengegrogurides.domain.service.GetUser;
import org.mmm.challengegrogurides.domain.service.GetVehicleFleet;
import org.mmm.challengegrogurides.domain.service.OrderRentVehicle;
import org.mmm.challengegrogurides.domain.service.SetVehicleFleet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = TheChallengeGroguRidesApplication.class)
public class BeanConfiguration {

    @Bean
    SetVehicleFleet setVehicleFleet(
            VehicleRepository vehicleRepository, UserRepository userRepository, RentRepository rentRepository){
        return new SetVehicleFleet(vehicleRepository, userRepository, rentRepository);
    }

    @Bean
    GetVehicleFleet getVehicleFleet(VehicleRepository vehicleRepository){
        return new GetVehicleFleet(vehicleRepository);
    }

    @Bean
    CreateUser createUser(UserRepository userRepository){
        return new CreateUser(userRepository);
    }

    @Bean
    GetUser getUser(UserRepository userRepository){
        return new GetUser(userRepository);
    }

    @Bean
    OrderRentVehicle orderRentVehicle(RentRepository rentRepository){
        return new OrderRentVehicle(rentRepository);
    }

    @Bean
    GetRentVehicle getRentVehicle(RentRepository rentRepository){
        return new GetRentVehicle(rentRepository);
    }

    @Bean
    DropOff dropOff(RentRepository rentRepository){
        return new DropOff(rentRepository);
    }
}
