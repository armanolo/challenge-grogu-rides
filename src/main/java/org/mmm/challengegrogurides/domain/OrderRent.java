package org.mmm.challengegrogurides.domain;

import org.mmm.challengegrogurides.domain.valueobject.AvailableSeats;
import org.mmm.challengegrogurides.domain.valueobject.EndRentTime;
import org.mmm.challengegrogurides.domain.valueobject.UserId;

import java.util.Objects;

public class OrderRent {
    private final UserId userId;
    private final EndRentTime endRentTime;

    private final AvailableSeats availableSeats;

    public OrderRent(UserId userId, EndRentTime endRentTime, AvailableSeats availableSeats) {
        this.userId = userId;
        this.endRentTime = endRentTime;
        this.availableSeats = availableSeats;
    }

    public UserId userId() {
        return userId;
    }

    public EndRentTime endRentTime() {
        return endRentTime;
    }

    public AvailableSeats availableSeats() {
        return availableSeats;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderRent that = (OrderRent) o;
        return Objects.equals(this.userId, that.userId) &&
                Objects.equals(this.endRentTime, that.endRentTime) &&
                Objects.equals(this.availableSeats, that.availableSeats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.userId, this.endRentTime, this.availableSeats);
    }

    @Override
    public String toString() {
        return "OrderRent{" +
                "userId=" + this.userId +
                ", endRentTime=" + this.endRentTime +
                ", availableSeats=" + this.availableSeats +
                '}';
    }
}
