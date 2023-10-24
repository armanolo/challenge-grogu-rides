package org.mmm.challengegrogurides.shared.mother;

import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.valueobject.Dni;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.domain.valueobject.UserName;

import java.util.UUID;

public class UserMother extends CommonMother{


    public static User valid() {
        return getUser(UUID.randomUUID().toString(), generateDni());
    }

    private static User getUser(String uuid, String dni){
        return new User(new UserId(uuid), new Dni(dni),new UserName("Manolo Martin"));
    }
}
