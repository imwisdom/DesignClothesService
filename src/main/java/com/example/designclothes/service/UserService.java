package com.example.designclothes.service;

import com.example.designclothes.domain.User;
import com.example.designclothes.repository.UserRepository;

import javax.transaction.Transactional;

@Transactional
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public Long join(User user){
        validateUserExist(user);
        userRepository.save(user);
        return user.getId();
    }

    public void validateUserExist(User user){
        userRepository.findByName(user.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("This name is exist.");
                });
    }

}
