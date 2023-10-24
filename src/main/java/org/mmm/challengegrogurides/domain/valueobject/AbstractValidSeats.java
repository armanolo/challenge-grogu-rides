package org.mmm.challengegrogurides.domain.valueobject;


import org.mmm.challengegrogurides.domain.exception.InvalidAvailableSeats;

import java.util.Objects;

abstract class AbstractValidSeats {
    private final int value;

    public AbstractValidSeats(Integer value) {
        if (null == value) {
            throw new InvalidAvailableSeats("Invalid seats: not seat value");
        }
        if( value < getMinimumValidSeat() || value > getMaxValidSeat()  ){
            throw new InvalidAvailableSeats(
                    String.format("Invalid seats: got %d when minimum is %d and the maximum is %d",
                            value,
                            getMinimumValidSeat(),
                            getMaxValidSeat()
                        )
            );
        }
        this.value = value;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractValidSeats validSeats = (AbstractValidSeats) o;
        return value == validSeats.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public abstract int getMinimumValidSeat();

    public abstract int getMaxValidSeat();

    @Override
    public String toString() {
        return String.valueOf(this.value);
    }
}
