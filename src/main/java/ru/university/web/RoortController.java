package ru.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.university.service.UserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RoortController {
    @Autowired
    UserService service;

    @GetMapping("/")
    public String root(){
        return "index";
    }

    @GetMapping("/users")
    public String getUsers(Model model){
        model.addAttribute("users",service.getAll());
        return "users";
    }

    @PostMapping("users")
    public String setUser(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:courses";
    }
}
