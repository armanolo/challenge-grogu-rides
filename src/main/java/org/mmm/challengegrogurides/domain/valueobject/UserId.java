package org.mmm.challengegrogurides.domain.valueobject;


import org.mmm.challengegrogurides.domain.exception.InvalidUserIdException;

import java.util.UUID;

public class UserId extends Id{
    public UserId(String value) throws InvalidUserIdException {
        try{
            this.uuid = UUID.fromString(value);
        }catch(IllegalArgumentException e){
            throw new InvalidUserIdException(
                    String.format("Invalid user id: %s",value)
            );
        }
    }
}
