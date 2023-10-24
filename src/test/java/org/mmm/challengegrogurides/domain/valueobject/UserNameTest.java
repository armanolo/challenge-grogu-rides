package org.mmm.challengegrogurides.domain.valueobject;

import org.junit.jupiter.api.Test;
import org.mmm.challengegrogurides.domain.exception.InvalidUserNameException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserNameTest {

    private InvalidUserNameException exceptionThrown;
    private UserName userName;

    private String INVALID_NAME_MESSAGE = "%s is an invalid name, at least with two words (+4letters)";

    @Test
    void user_name() {
        userName = new UserName("Manolo Martin");
        assertThat(userName.value()).isEqualTo("Manolo Martin");

        userName = new UserName("Miguel Angel Martin");
        assertThat(userName.value()).isEqualTo("Miguel Angel Martin");

        userName = new UserName("John Doe");
        assertThat(userName.value()).isEqualTo("John Doe");

        exceptionThrown = assertThrows(
                InvalidUserNameException.class,
                () -> new UserName("Manolo")
        );
        assertThat(
                String.format(INVALID_NAME_MESSAGE, "Manolo")
        ).isEqualTo(exceptionThrown.getMessage());

        exceptionThrown = assertThrows(
                InvalidUserNameException.class,
                () -> new UserName("J D")
        );
        assertThat(
                String.format(INVALID_NAME_MESSAGE, "J D")
        ).isEqualTo(exceptionThrown.getMessage());

    }
}
