package org.mmm.challengegrogurides.domain.service;

import org.mmm.challengegrogurides.domain.User;
import org.mmm.challengegrogurides.domain.exception.DuplicatedDniException;
import org.mmm.challengegrogurides.domain.repository.UserRepository;

public class CreateUser {
    private final UserRepository userRepository;

    public CreateUser(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void execute(User user){
        if(userRepository.existUserByDni(user.dni())){
            throw new DuplicatedDniException(
                    String.format("Duplicated dni: %s",user.dni().value())
            );
        }
        userRepository.createUser(user);
    }
}
