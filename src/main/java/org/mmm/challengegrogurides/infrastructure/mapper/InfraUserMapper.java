package org.mmm.challengegrogurides.infrastructure.mapper;

import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.valueobject.Dni;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.domain.valueobject.UserName;
import org.mmm.challengegrogurides.infrastructure.persistence.model.UserDB;
import org.springframework.stereotype.Component;

@Component
public class InfraUserMapper {

    public User entityToDomain(UserDB userDB){
        return new User(
                new UserId(userDB.getId().toString()),
                new Dni(userDB.getDni()),
                new UserName(userDB.getName())
        );
    }

    public UserDB domainToEntity(User user){
        return new UserDB(user.id().uuid(), user.dni().value(), user.name().value());
    }
}
