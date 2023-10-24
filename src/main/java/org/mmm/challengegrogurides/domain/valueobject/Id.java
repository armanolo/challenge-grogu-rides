package org.mmm.challengegrogurides.domain.valueobject;

import java.util.Objects;
import java.util.UUID;

public abstract class Id {

    protected UUID uuid;

    public String value(){
        return this.uuid.toString();
    }

    public UUID uuid(){
        return this.uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Id id = (Id) o;
        return value().equals(id.value());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value());
    }

    @Override
    public String toString() {
        return uuid.toString();
    }
}
