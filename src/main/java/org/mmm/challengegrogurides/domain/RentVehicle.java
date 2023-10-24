package org.mmm.challengegrogurides.domain;

import org.mmm.challengegrogurides.domain.valueobject.EndRentTime;
import org.mmm.challengegrogurides.domain.valueobject.RentVehicleId;
import org.mmm.challengegrogurides.domain.valueobject.ReturnTime;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.domain.valueobject.VehicleId;

import java.time.LocalDateTime;
import java.util.Objects;

public class RentVehicle {
    private final RentVehicleId id;
    private final VehicleId vehicleId;
    private final UserId userId;
    private final EndRentTime endRentTime;

    private ReturnTime returnTime;

    public RentVehicle(RentVehicleId id,
                       VehicleId vehicleId,
                       UserId userId,
                       EndRentTime endRentTime,
                       ReturnTime returnTime) {
        this.id = id;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.endRentTime = endRentTime;
        this.returnTime = returnTime;
    }

    public RentVehicleId id() {
        return this.id;
    }
    public VehicleId vehicleId() {
        return this.vehicleId;
    }
    public UserId userId() {
        return userId;
    }

    public EndRentTime endRentTime() {
        return endRentTime;
    }

    public ReturnTime returnTime() {
        return this.returnTime;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RentVehicle that = (RentVehicle) o;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.vehicleId, that.vehicleId) &&
                Objects.equals(this.userId, that.userId) &&
                Objects.equals(this.endRentTime, that.endRentTime) &&
                Objects.equals(this.returnTime, that.returnTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id,
                this.vehicleId,
                this.userId,
                this.endRentTime);
    }

    @Override
    public String toString() {
        return "RentVehicle{" +
                "id=" + this.id +
                ", vehicleId=" + this.vehicleId +
                ", userId=" + this.userId +
                ", endRentTime=" + this.endRentTime +
                ", returnTime=" + this.returnTime +
                '}';
    }

    public void setReturnTime(LocalDateTime now) {
        this.returnTime = new ReturnTime(now);
    }
}
