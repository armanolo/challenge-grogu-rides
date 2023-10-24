package org.mmm.challengegrogurides.shared.mother;

import org.mmm.challengegrogurides.infrastructure.controller.filter.UserDto;

import java.util.UUID;

public class UserDtoMother extends CommonMother{


    public static UserDto valid() {
        return getUserDto(UUID.randomUUID().toString(), generateDni(),"Manolo Martin");//faker.name().fullName()
    }

    public static UserDto invalidShortName() {
        return getUserDto(UUID.randomUUID().toString(), generateDni(), "DJ");
    }

    public static UserDto invalidDni() {
        return getUserDto(UUID.randomUUID().toString(), "123456789X", "Juan Garcia");
    }

    public static UserDto invalidUserId() {
        return getUserDto("SDL", generateDni(), "Juan Garcia");
    }

    private static UserDto getUserDto(String uuid, String dni, String name){
        return new UserDto(uuid, dni, name);
    }
}
