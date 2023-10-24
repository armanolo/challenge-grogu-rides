package org.mmm.challengegrogurides.infrastructure.persistence.repositories;

import org.mmm.challengegrogurides.infrastructure.persistence.model.UserDB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaUserRepository extends JpaRepository<UserDB, UUID> {

    Optional<UserDB> findById(UUID id);

    @Query("select case when (count(u) > 0)  then true else false end from users u where u.dni = :dni")
    boolean findByDni(String dni);
}
