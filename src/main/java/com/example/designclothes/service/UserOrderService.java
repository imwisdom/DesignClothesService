package com.example.designclothes.service;

import com.example.designclothes.domain.UserOrder;
import com.example.designclothes.repository.UserOrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public UserOrderService(UserOrderRepository userOrderRepository){
        this.userOrderRepository = userOrderRepository;
    }
    public UserOrder saveOrder(UserOrder userOrder){
        return this.userOrderRepository.save(userOrder);
    }
    public List<UserOrder> getOrderList(String userName){
        Optional<List<UserOrder>> list = this.userOrderRepository.findByUserName(userName);
        if(list.isEmpty()) return null;
        else return list.get();
    }
    public List<UserOrder> getAllOrderList(){
        Optional<List<UserOrder>> list = this.userOrderRepository.findAll();
        if(list.isEmpty()) return null;
        else return list.get();
    }

}
