package com.example.designclothes.service;

import com.example.designclothes.controller.UserForm;
import com.example.designclothes.controller.UserRegisterForm;
import com.example.designclothes.domain.User;
import com.example.designclothes.repository.UserRepository;

import javax.transaction.Transactional;

@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public Long join(UserRegisterForm userRegisterForm){
        if(validateUserExist(userRegisterForm)) return -1L;
        if(validatePasswordSame(userRegisterForm)) return -2L;

        User user = new User();
        user.setName(userRegisterForm.getName());
        user.setPassword(userRegisterForm.getPassword());
        userRepository.save(user);

        return user.getId();
    }

    public boolean validateUserExist(UserRegisterForm userRegisterForm){
        return !userRepository.findByName(userRegisterForm.getName()).isEmpty();
    }
    public boolean validatePasswordSame(UserRegisterForm userRegisterForm){
        return !userRegisterForm.getPassword().equals(userRegisterForm.getCpassword());
    }
    public boolean login(UserForm userForm){
        if(userRepository.findByName(userForm.getName()).isEmpty()) return false;
        String correctPassword = userRepository.findByName(userForm.getName()).get().getPassword();
        return userForm.getPassword().equals(correctPassword);
    }
    public boolean isAdmin(String userName){
        if(userRepository.findByName(userName).isEmpty()) return false;
        return userRepository.findByName(userName).get().getIsAdmin();
    }

}
