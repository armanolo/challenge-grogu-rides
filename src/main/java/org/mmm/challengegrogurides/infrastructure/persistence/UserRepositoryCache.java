package org.mmm.challengegrogurides.infrastructure.persistence;

import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.repository.UserRepository;
import org.mmm.challengegrogurides.domain.valueobject.Dni;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.infrastructure.mapper.InfraUserMapper;
import org.mmm.challengegrogurides.infrastructure.persistence.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserRepositoryCache implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    private final InfraUserMapper infraUserMapper;

    @Autowired
    public UserRepositoryCache(JpaUserRepository jpaUserRepository, InfraUserMapper infraUserMapper) {
        this.jpaUserRepository = jpaUserRepository;
        this.infraUserMapper = infraUserMapper;
    }

    @Override
    public void deleteAll() {
        this.jpaUserRepository.deleteAll();
    }

    @Override
    public void createUser(User user) {
        jpaUserRepository.save(
                infraUserMapper.domainToEntity(user)
        );
    }

    @Override
    public boolean existUserByDni(Dni dni) {
        return jpaUserRepository.findByDni(dni.value());
    }

    @Override
    public Optional<User> getUser(UserId userId) {
        return jpaUserRepository.findById(userId.uuid())
                .map(infraUserMapper::entityToDomain);
    }
}
