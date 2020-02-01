package ru.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.university.service.CourseService;

import javax.servlet.http.HttpServletRequest;

import static ru.university.web.SecurityUtil.authUserId;

@Controller
public class RootController {

    @Autowired
    CourseService courseService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/users")
    public String getUsers() {
        return "users";
    }

    @PostMapping("users")
    public String setUser(HttpServletRequest request) {
        int userId = Integer.parseInt(request.getParameter("userId"));
        SecurityUtil.setAuthUserId(userId);
        return "redirect:courses";
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        model.addAttribute("courses", courseService.getAll(authUserId()));
        return "courses";
    }
}
