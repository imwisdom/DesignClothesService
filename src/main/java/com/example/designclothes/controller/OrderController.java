package com.example.designclothes.controller;

import com.example.designclothes.domain.UserOrder;
import com.example.designclothes.service.UserOrderService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class OrderController {

    private UserOrderService userOrderService;

    @Autowired
    public OrderController(UserOrderService userOrderService){
        this.userOrderService = userOrderService;
    }

    @PostMapping("/order")
    public ModelAndView orderDesign(HttpServletRequest request,
                                    @RequestParam(name="id") Long id){
        HttpSession session = request.getSession();
        UserOrder userOrder = new UserOrder();
        userOrder.setDesignId(id);
        userOrder.setUserName((String) session.getAttribute("username"));
        userOrderService.saveOrder(userOrder);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("clothes");
        return mav;
    }
}
