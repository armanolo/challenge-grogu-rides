package org.mmm.challengegrogurides.domain.valueobject;

public class AvailableSeats extends AbstractValidSeats {
    public AvailableSeats(Integer value) {
        super(value);
    }

    @Override
    public int getMinimumValidSeat() {
        return 1;
    }

    @Override
    public int getMaxValidSeat() {
        return 6;
    }
}
