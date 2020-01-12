package ru.university.web.course;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.university.model.Course;
import ru.university.web.SecurityUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/courses")
public class JspCourseController extends AbstractCourseController {

    @GetMapping("/between")
    public String getBetween(HttpServletRequest request, Model model) {
        float startCost=Float.parseFloat(request.getParameter("startCost"));
        float endCost =Float.parseFloat(request.getParameter("endCost"));
        model.addAttribute("courses", super.getBetween(startCost,endCost));
        return "courses";
    }
    @GetMapping("/delete")
    public String delete(HttpServletRequest request){
        super.delete(getId(request));
        return "redirect:courses";
    }

    @GetMapping("/update")
    public String update(HttpServletRequest request,Model model){
        model.addAttribute("course", super.get(getId(request)));
        return "courseForm";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("course", new Course("Name",0,0f));
        return "courseForm";
    }

    @PostMapping
    public String updateOrCreate(HttpServletRequest request){
        Course course = new Course(request.getParameter("name"),
                Integer.parseInt(request.getParameter("number")),
                Float.parseFloat(request.getParameter("cost")));

        if(request.getParameter("id").isEmpty()){
            super.create(course);
        } else {
            super.update(course,getId(request));
        }
        return "redirect:courses";
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
