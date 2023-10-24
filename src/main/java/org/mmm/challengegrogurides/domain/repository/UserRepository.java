package org.mmm.challengegrogurides.domain.repository;

import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.valueobject.Dni;
import org.mmm.challengegrogurides.domain.valueobject.UserId;

import java.util.Optional;


public interface UserRepository {
    void deleteAll();
    void createUser(User user);
    boolean existUserByDni(Dni dni);
    Optional<User> getUser(UserId userId);
}
