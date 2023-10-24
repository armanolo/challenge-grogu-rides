package org.mmm.challengegrogurides.application.service;

import org.mmm.challengegrogurides.application.service.mapper.UserMapper;
import org.mmm.challengegrogurides.domain.service.CreateUser;
import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {
    private final CreateUser createUser;

    private final UserMapper userMapper;

    @Autowired
    public CreateUserService(CreateUser createUser, UserMapper userMapper){
        this.createUser = createUser;
        this.userMapper = userMapper;
    }

    public void execute(UserDto userDto){
        createUser.execute(
                userMapper.dtoToDomain(userDto)
        );
    }
}
