package com.example.designclothes.service;

import com.example.designclothes.controller.UserForm;
import com.example.designclothes.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Test
    void join() {
        UserForm form = new UserForm();
        form.setName("wisdom");
        form.setPassword("1234");
        form.setCpassword("1234");

        Long id = userService.join(form);
        assertNotNull(id);
    }

    @Test
    void validateUserExist() {
    }

    @Test
    void validatePasswordSame() {
    }
}