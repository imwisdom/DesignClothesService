package com.example.designclothes.controller;

import com.example.designclothes.domain.User;
import com.example.designclothes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/register")
    public String registerForm(){
        return "register";
    }

    @PostMapping("/register")
    public String register(UserForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Long regId = userService.join(form);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        if(regId == -1L){
            out.println("<script>alert('이미 사용중인 이름입니다.')</script>");
            out.flush();
            return "register";
        }
        else if(regId == -2L){
            out.println("<script>alert('비밀번호와 확인용 비밀번호가 다릅니다.')</script>");
            out.flush();
            return "register";
        }
        else{
            out.println("<script>alert('회원가입이 완료 되었습니다.')</script>");
            out.flush();
            return "index.html";
        }
    }
}
