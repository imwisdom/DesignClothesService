package com.example.designclothes.controller;

import com.example.designclothes.domain.Design;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class UserOrderController {

    private UserOrderService userOrderService;

    @Autowired
    public UserOrderController(UserOrderService userOrderService){
        this.userOrderService = userOrderService;
    }

    @PostMapping("/order")
    public ModelAndView orderDesign(HttpServletRequest request,
                                    @RequestParam(name="id") Long id){
        HttpSession session = request.getSession();
        UserOrder userOrder = new UserOrder();
        userOrder.setDesignId(id);
        userOrder.setUserName((String) session.getAttribute("username"));
        userOrder.setDate(LocalDateTime.now());
        userOrderService.saveOrder(userOrder);

        ModelAndView mav = new ModelAndView();
        mav.setViewName("clothes");
        return mav;
    }
    @GetMapping("/manage")
    public ModelAndView manage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();

        ModelAndView mav = new ModelAndView();
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("is_admin", session.getAttribute("is_admin"));

        if(session.getAttribute("is_admin")==null || !(boolean)session.getAttribute("is_admin")){
            mav.setViewName("redirect:/");
        }else{
            List<UserOrder> list = userOrderService.getAllOrderList();
            model.addAttribute("userOrderList", list);
            mav.setViewName("manage");
        }
        return mav;
    }
    @PostMapping("/manage")
    public ModelAndView checkOrder(HttpServletRequest request, Model model,
                                   @RequestParam("id") Long id){
        ModelAndView mav = new ModelAndView();
        userOrderService.checkOrder(id);

        mav.setViewName("manage");
        return mav;
    }
}
