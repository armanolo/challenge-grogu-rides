package org.mmm.challengegrogurides.domain.repository;

import org.mmm.challengegrogurides.domain.Vehicle;
import java.util.List;

public interface VehicleRepository {
    void setVehicles(List<Vehicle> cars);
    void deleteVehicles();
    List<Vehicle> getVehicles();
}
