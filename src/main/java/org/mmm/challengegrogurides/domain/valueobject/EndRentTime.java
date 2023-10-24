package org.mmm.challengegrogurides.domain.valueobject;

import org.mmm.challengegrogurides.domain.exception.InvalidEndTimeException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class EndRentTime {
    private final LocalDateTime value;
    private static final int MINIMUM_DURATION = 3600;//1hour
    public EndRentTime(LocalDateTime endTime) {
        try{
            LocalDateTime now = LocalDateTime.now();
            long duration = Duration.between(now, endTime).getSeconds();
            if( duration < MINIMUM_DURATION){
                if(duration < 0){
                    throw new InvalidEndTimeException("The time selected has passed");
                }
                throw new InvalidEndTimeException(
                        String.format("Duration minimum is one hour not %s", timeConvert(duration))
                );
            }
        }catch (InvalidEndTimeException e){
            throw e;
        }catch (Exception e){
            throw new InvalidEndTimeException("Invalid time");
        }

        this.value = endTime;
    }

    public String timeConvert(long time) {
        StringBuilder stringBuilder = new StringBuilder();
        int day = (int) time/(3600*24);
        int hour = (int) time/(3600);
        int minutes = (int) time/(60);
        if ( day > 0) {
            stringBuilder.append(String.format("%d days",day));
        }
        if ( hour > 0 ) {
            stringBuilder.append(String.format("%d hours",hour));
        }
        if ( minutes > 0 ) {
            stringBuilder.append(String.format("%d minutes",minutes));
        }
        return stringBuilder.toString();
    }

    public LocalDateTime value(){
        return this.value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EndRentTime endRentTime = (EndRentTime) o;
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
