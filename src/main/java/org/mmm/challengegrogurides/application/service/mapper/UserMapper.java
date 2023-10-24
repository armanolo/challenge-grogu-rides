package org.mmm.challengegrogurides.application.service.mapper;

import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.domain.valueobject.Dni;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.domain.valueobject.UserName;
import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;
import org.springframework.stereotype.Component;


@Component
public class UserMapper {

    public User dtoToDomain(UserDto userDto) throws InvalidUserIdException {
        return new User(new UserId(userDto.id()), new Dni(userDto.dni()), new UserName(userDto.name()));
    }

    public UserDto domainToDto(User user) {
        return new UserDto(user.id().value(), user.dni().value(), user.name().value());
    }
}
