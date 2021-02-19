package com.example.designclothes.service;

import com.example.designclothes.controller.UserForm;
import com.example.designclothes.domain.User;
import com.example.designclothes.repository.UserRepository;

import javax.transaction.Transactional;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public Long join(User user, UserForm userForm){
        validateUserExist(user);
        validatePasswordSame(userForm);

        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        userRepository.save(user);

        return user.getId();
    }

    public void validateUserExist(User user){
        userRepository.findByName(user.getName())
                .ifPresent(m->{
                    throw new IllegalStateException("This name is exist.");
                });
    }
    public void validatePasswordSame(UserForm userForm){
        if(!userForm.getPassword().equals(userForm.getCpassword())){
            throw new IllegalStateException("These passwords is different.");
        }
    }

}
