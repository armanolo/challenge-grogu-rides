package org.mmm.challengegrogurides.domain;

import org.mmm.challengegrogurides.domain.valueobject.AvailableSeats;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;

import java.util.Objects;

public class Vehicle {
    private final VehicleId id;
    private final AvailableSeats availableSeats;

    public Vehicle(VehicleId id, AvailableSeats availableSeats) {
        this.id = id;
        this.availableSeats = availableSeats;
    }

    public VehicleId id() {
        return id;
    }

    public AvailableSeats availableSeats() {
        return availableSeats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(id, vehicle.id) &&
                Objects.equals(availableSeats, vehicle.availableSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, availableSeats);
    }

    @Override
    public String toString() {
        return String.format("Vehicle{id=%s, availableSeats=%d}",id().value(), availableSeats().value());
    }
}
