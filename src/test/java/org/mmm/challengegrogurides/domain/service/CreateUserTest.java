package org.mmm.challengegrogurides.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.exception.DuplicatedDniException;
import org.mmm.challengegrogurides.domain.repository.UserRepository;
import org.mmm.challengegrogurides.shared.mother.UserMother;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateUserTest {

    @Mock
    UserRepository userRepository;
    CreateUser setUser;

    @BeforeEach
    void setup(){
        setUser = new CreateUser(userRepository);
    }

    @Test
    void execute_with_right_result() {
        User user = UserMother.valid();
        setUser.execute(user);
        then(userRepository).should(times(1)).createUser(user);
    }

    @Test
    void throw_error_user_with_dni_duplicated() {
        User user = UserMother.valid();
        when(userRepository.existUserByDni(user.dni())).thenReturn(true);
        assertThatThrownBy(
                () -> setUser.execute(user))
                .isInstanceOf(DuplicatedDniException.class)
                .hasMessage(String.format("Duplicated dni: %s",user.dni().value()));

        verify(userRepository,times(1)).existUserByDni(user.dni());
        verify(userRepository,times(0)).createUser(user);
    }
}
