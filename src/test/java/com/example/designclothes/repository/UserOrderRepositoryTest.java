package com.example.designclothes.repository;

import com.example.designclothes.domain.UserOrder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Transactional
public class UserOrderRepositoryTest {
    @Autowired
    UserOrderRepository repository;

    @Test
    void remove(){
        int N = repository.findAll().get().size();
        UserOrder userOrder = repository.remove(5L);
        assertNotEquals(repository.findAll().get().size(), N);
    }

}

