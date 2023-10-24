package org.mmm.challengegrogurides.application.service;

import org.mmm.challengegrogurides.application.service.mapper.UserMapper;
import org.mmm.challengegrogurides.domain.service.GetUser;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {
    private final GetUser getUser;

    private final UserMapper userMapper;

    @Autowired
    public GetUserService(GetUser getUser, UserMapper userMapper){
        this.getUser = getUser;
        this.userMapper = userMapper;
    }

    public UserDto execute(String uuid){
        return userMapper.domainToDto(
            getUser.execute(new UserId(uuid))
        );
    }
}
