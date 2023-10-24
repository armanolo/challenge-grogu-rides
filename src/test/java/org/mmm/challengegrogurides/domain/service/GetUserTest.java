package org.mmm.challengegrogurides.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.exception.NotFoundUserException;
import org.mmm.challengegrogurides.domain.repository.UserRepository;
import org.mmm.challengegrogurides.domain.valueobject.UserId;
import org.mmm.challengegrogurides.shared.mother.UserMother;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetUserTest {

    @Mock
    UserRepository userRepository;
    GetUser getUser;

    @BeforeEach
    void setup(){
        getUser = new GetUser(userRepository);
    }

    @Test
    void execute_with_right_result() {
        User user = UserMother.valid();
        when(userRepository.getUser(user.id())).thenReturn(Optional.of(user));
        getUser.execute(user.id());
        verify(userRepository,times(1)).getUser(user.id());
    }

    @Test
    void throw_error_user_not_found() {
        String id = UUID.randomUUID().toString();
        UserId userId = new UserId(id);
        when(userRepository.getUser(userId)).thenReturn(Optional.empty());
        assertThatThrownBy(
                () -> getUser.execute(userId))
                .isInstanceOf(NotFoundUserException.class)
                .hasMessage(String.format("Not found user with id %s",id));
        verify(userRepository,times(1)).getUser(new UserId(id));
    }
}
