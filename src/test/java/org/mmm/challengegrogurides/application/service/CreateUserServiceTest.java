package org.mmm.challengegrogurides.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.application.service.mapper.UserMapper;
import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.exception.InvalidDniException;
import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;
import org.mmm.challengegrogurides.domain.exception.InvalidUserNameException;
import org.mmm.challengegrogurides.domain.service.CreateUser;
import org.mmm.challengegrogurides.domain.valueobject.Dni;
import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;
import org.mmm.challengegrogurides.shared.mother.UserDtoMother;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    CreateUserService setUserService;

    @Mock
    CreateUser setUser;

    @InjectMocks
    UserMapper userMapper;

    @BeforeEach
    void setup(){
        setUserService = new CreateUserService(setUser, userMapper);
    }

    @Test
    void execute() {
        UserDto userDto = UserDtoMother.valid();
        User user = userMapper.dtoToDomain(userDto);
        setUserService.execute(userDto);
        then(setUser).should(times(1)).execute(user);
    }

    @Test
    void throw_error_invalid_user_id() {
        UserDto userDto = UserDtoMother.invalidUserId();
        String id = userDto.id();

        InvalidUserIdException exceptionThrown = assertThrows(
                InvalidUserIdException.class,
                () -> setUserService.execute(userDto)
        );
        String expectedMessage = String.format("Invalid user id: %s",id);
        assertEquals(expectedMessage, exceptionThrown.getMessage());
        then(setUser).should(times(0)).execute(any());
    }

    @Test
    void execute_with_error_invalid_user_name() {
        UserDto userDto = UserDtoMother.invalidShortName();
        InvalidUserNameException exceptionThrown = assertThrows(
                InvalidUserNameException.class,
                () -> setUserService.execute(userDto)
        );
        String expectedMessage = String.format(
                "%s is an invalid name, at least with two words (+4letters)",userDto.name());
        assertEquals(expectedMessage, exceptionThrown.getMessage());
        then(setUser).should(times(0)).execute(any());
    }

    @Test
    void execute_with_error_invalid_dni() {
        UserDto userDto = UserDtoMother.invalidDni();
        InvalidDniException exceptionThrown = assertThrows(
                InvalidDniException.class,
                () -> setUserService.execute(userDto)
        );
        String expectedMessage = String.format("Dni has not the format: %s", Dni.FORMAT_DNI);
        assertEquals(expectedMessage, exceptionThrown.getMessage());
        then(setUser).should(times(0)).execute(any());
    }
}
