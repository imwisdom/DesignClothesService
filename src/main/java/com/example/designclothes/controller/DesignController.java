package com.example.designclothes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class DesignController {

    @GetMapping("/design")
    public String designPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("username", session.getAttribute("username"));

        return "design";
    }
}
