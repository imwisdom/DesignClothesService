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
    public Long join(UserForm userForm){
        if(validateUserExist(userForm)) return -1L;
        if(validatePasswordSame(userForm)) return -2L;

        User user = new User();
        user.setName(userForm.getName());
        user.setPassword(userForm.getPassword());
        userRepository.save(user);

        return user.getId();
    }

    public boolean validateUserExist(UserForm userForm){
        return !userRepository.findByName(userForm.getName()).isEmpty();
    }
    public boolean validatePasswordSame(UserForm userForm){
        return !userForm.getPassword().equals(userForm.getCpassword());
    }

}
