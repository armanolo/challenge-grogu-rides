package org.mmm.challengegrogurides.domain;

import org.mmm.challengegrogurides.domain.valueobject.Dni;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.domain.valueobject.UserName;

import java.util.Objects;

public class User {
    private final UserId id;
    private final Dni dni;
    private final UserName name;

    public User(UserId id, Dni dni, UserName name) {
        this.id = id;
        this.dni = dni;
        this.name = name;
    }

    public UserId id() {
        return id;
    }

    public Dni dni() {
        return dni;
    }

    public UserName name() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(dni, user.dni) &&
                Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dni, name);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", dni=" + dni +
                ", name=" + name +
                '}';
    }

}
