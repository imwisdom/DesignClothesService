package com.example.designclothes.controller;

import com.example.designclothes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    public String registerPage(){
        return "register";
    }
    @PostMapping("/register")
    public String register(UserRegisterForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            return "index";
        }
    }
    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }
    @PostMapping("/login")
    public String login(UserForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isLoginSuccess = userService.login(form);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        if(!isLoginSuccess){
            out.println("<script>alert('아이디와 비밀번호를 다시 확인하십시오.')</script>");
            out.flush();
            return "login";
        }

        HttpSession session = request.getSession();
        session.setAttribute("name", form.getName());
        out.println("<script>alert('로그인에 성공하셨습니다.')</script>");
        out.flush();
        return "index";
    }

}
