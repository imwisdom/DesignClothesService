package com.example.designclothes.service;

import com.example.designclothes.repository.DesignRepository;
import com.example.designclothes.repository.UserOrderRepository;
import com.example.designclothes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em){
        this.dataSource = dataSource;
        this.em = em;
    }
    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new UserRepository(em);
    }

    @Bean
    public DesignService designService(){ return new DesignService(designRepository());}

    @Bean
    public DesignRepository designRepository(){return new DesignRepository(em);}

    @Bean
    public UserOrderService orderService(){return new UserOrderService(orderRepository());}

    @Bean
    public UserOrderRepository orderRepository(){return new UserOrderRepository(em);}

}
