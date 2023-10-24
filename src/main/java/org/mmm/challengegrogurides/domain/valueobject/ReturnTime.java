package org.mmm.challengegrogurides.domain.valueobject;


import java.time.LocalDateTime;
import java.util.Objects;

public class ReturnTime {
    private final LocalDateTime value;
    public ReturnTime(LocalDateTime endTime) {
        this.value = endTime;
    }

    public LocalDateTime value(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReturnTime endRentTime = (ReturnTime) o;
        return value().equals(endRentTime.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value());
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
