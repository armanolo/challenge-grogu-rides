package org.mmm.challengegrogurides.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.mapper.UserMapper;
import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.domain.service.GetUser;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;
import org.mmm.challengegrogurides.shared.mother.UserDtoMother;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.BDDMockito.times;

@ExtendWith(MockitoExtension.class)
class GetUserServiceTest {

    GetUserService getUserService;

    @Mock
    GetUser getUser;

    @InjectMocks
    UserMapper userMapper;

    @BeforeEach
    void setup(){
        getUserService = new GetUserService(getUser, userMapper);
    }

    @Test
    void execute() {
        UserDto userDto = UserDtoMother.valid();
        User user = userMapper.dtoToDomain(userDto);
        given(getUser.execute(new UserId(userDto.id()))).willReturn(user);
        UserDto userReceived = getUserService.execute(userDto.id());
        then(getUser).should(times(1)).execute(new UserId(userDto.id()));
        assertEquals(userReceived, userDto);
    }

    @Test
    void throw_error_invalid_user_id() {
        UserDto userDto = UserDtoMother.invalidUserId();
        String id = userDto.id();

        InvalidUserIdException exceptionThrown = assertThrows(
                InvalidUserIdException.class,
                () -> getUserService.execute(id)
        );
        String expectedMessage = String.format("Invalid user id: %s",id);
        assertEquals(expectedMessage, exceptionThrown.getMessage());
        then(getUser).should(times(0)).execute(any());
    }
}
