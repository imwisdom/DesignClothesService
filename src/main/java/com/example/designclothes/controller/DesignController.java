package com.example.designclothes.controller;

import com.example.designclothes.domain.Design;
import com.example.designclothes.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class DesignController {

    private DesignService designService;
    @Autowired
    public DesignController(DesignService designService){
        this.designService = designService;
    }
    @GetMapping("/design")
    public String designPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("username", session.getAttribute("username"));

        return "design";
    }
    @PostMapping("/design")
    public String saveDesign(HttpServletRequest request, @RequestParam("photo") String photo,
                             @RequestParam("price") Integer price) throws IOException {
        String photoData = photo.split(",")[1];

        HttpSession session = request.getSession();
        String userName = (String)session.getAttribute("username");

        String fileRoute = designService.saveDesign(userName, photoData);
        designService.saveDesignInfo(userName, fileRoute, price);

        return "design";
    }

    @GetMapping("/clothes")
    public String clothesPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("username", session.getAttribute("username"));

        List<Design> designList = designService.loadDesign((String)session.getAttribute("username"));
        if(designList!=null && designList.size()>0)
            model.addAttribute("designList", designList);

        return "clothes";
    }

}
