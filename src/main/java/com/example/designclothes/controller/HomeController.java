package com.example.designclothes.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("is_admin", session.getAttribute("is_admin"));

        return "index";
    }

}
