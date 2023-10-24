package org.mmm.challengegrogurides.domain.service;

import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.exception.NotFoundUserException;
import org.mmm.challengegrogurides.domain.repository.UserRepository;
import org.mmm.challengegrogurides.domain.valueobject.UserId;

import java.util.Optional;

public class GetUser {
    private final UserRepository userRepository;

    public GetUser(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User execute(UserId userId){
        Optional<User> optionalUser = userRepository.getUser(userId);
        if (optionalUser.isEmpty()) {
            throw new NotFoundUserException(
                    String.format("Not found user with id %s",userId.value())
            );
        }
        return optionalUser.get();
    }
}
