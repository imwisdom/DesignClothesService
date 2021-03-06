package com.example.designclothes.controller;

import com.example.designclothes.domain.Design;
import com.example.designclothes.service.DesignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class DesignController {

    private DesignService designService;
    @Autowired
    public DesignController(DesignService designService){
        this.designService = designService;
    }
    @GetMapping("/design")
    public ModelAndView designPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("is_admin", session.getAttribute("is_admin"));

        ModelAndView mav = new ModelAndView();

        if(session.getAttribute("username") == null){
            mav.setViewName("redirect:/login");
        }
        else{
            mav.setViewName("design");
        }
        return mav;
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
    public ModelAndView clothesPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("is_admin", session.getAttribute("is_admin"));

        ModelAndView mav = new ModelAndView();

        if(session.getAttribute("username") == null){
            mav.setViewName("redirect:/login");
        }
        else{
            List<Design> designList = designService.loadDesign((String)session.getAttribute("username"));
            if(designList!=null && designList.size()>0)
                model.addAttribute("designList", designList);

            mav.setViewName("clothes");
        }

        return mav;
    }
    @GetMapping("/detail")
    public ModelAndView detailedDesignPage(HttpServletRequest request, Model model,
                                           @RequestParam("id") Long designId){
        HttpSession session = request.getSession();
        Design design = designService.loadDesign(designId);
        ModelAndView mav = new ModelAndView();
        model.addAttribute("username", session.getAttribute("username"));
        model.addAttribute("is_admin", session.getAttribute("is_admin"));

        if(!design.getUserName().equals(session.getAttribute("username"))
                && (session.getAttribute("is_admin")==null || !(boolean)session.getAttribute("is_admin"))){
            mav.setViewName("design");
        }
        else{
            model.addAttribute("design", design);
            mav.setViewName("detail");
        }
        return mav;
    }
}
