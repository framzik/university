package ru.university.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.university.service.CourseService;

import javax.servlet.http.HttpServletRequest;

import static ru.university.web.SecurityUtil.authUserId;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:courses";
    }

    @Secured("ROLE_PROFESSOR")
    @GetMapping("/users")
    public String getUsers() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/courses")
    public String getCourses(Model model) {
        return "courses";
    }
}
