package com.example.designclothes.repository;

import com.example.designclothes.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserRepositoryTest {

    @Autowired
    UserRepository repository;
    @Test
    void save() {
        User user = new User();
        user.setName("wisdom");
        user.setPassword("1234");

        User newUser = repository.save(user);
        assertEquals(user.getName(), newUser.getName());
    }

    @Test
    void findById() {
    }

    @Test
    void findByName() {
    }
}