package org.mmm.challengegrogurides.shared.mother;

import net.datafaker.Faker;
import org.mmm.challengegrogurides.domain.valueobject.Dni;

public abstract class CommonMother {
    static Faker faker;

    static {
        faker = new Faker();
    }

    public static String generateDni(){
        return faker.regexify(Dni.FORMAT_DNI);
    }

}
