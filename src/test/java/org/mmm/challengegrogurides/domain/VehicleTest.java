package org.mmm.challengegrogurides.domain;

import org.junit.jupiter.api.Test;
import org.mmm.challengegrogurides.shared.mother.VehicleMother;

import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class VehicleTest {

    @Test
    void testEquals() {
        UUID uuid = UUID.randomUUID();
        Vehicle vehicle = VehicleMother.validVehicleWithUuidAndSeats(uuid, 3);
        Vehicle vehicle1 = VehicleMother.validVehicleWithUuidAndSeats(uuid, 3);
        assertThat(vehicle.equals(vehicle1)).isTrue();
    }

    @Test
    void testNotEquals() {
        Vehicle vehicle = VehicleMother.validVehicle();
        Vehicle vehicle1 = VehicleMother.validVehicle();
        assertThat(vehicle.equals(vehicle1)).isFalse();
    }

    @Test
    void testHashCode() {
        Vehicle vehicle = VehicleMother.validVehicle();
        int hashExpected = Objects.hash(vehicle.id(), vehicle.availableSeats());
        assertThat(vehicle.hashCode()).isEqualTo(hashExpected);
    }

    @Test
    void testToString() {
        Vehicle vehicle = VehicleMother.validVehicle();
        String stringBuilder = "Vehicle{" + "id=" + vehicle.id().value() +
                ", " +
                "availableSeats=" + vehicle.availableSeats().value() +
                "}";

        assertThat(vehicle.toString()).hasToString(stringBuilder);
    }
}
