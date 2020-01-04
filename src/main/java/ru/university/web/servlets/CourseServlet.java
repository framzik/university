package ru.university.web.servlets;

import org.springframework.util.StringUtils;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.model.Course;
import ru.university.web.rest.CourseRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;


public class CourseServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private CourseRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/spring-db.xml");
        controller = springContext.getBean(CourseRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Course course = new Course(req.getParameter("name"),
                Integer.parseInt(req.getParameter("number")),
                Float.parseFloat(req.getParameter("cost")));

        if(StringUtils.isEmpty(req.getParameter("id"))){
            controller.create(course);
        }
        else {
            controller.update(course,getId(req));
        }
        resp.sendRedirect("courses");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                controller.delete(id);
                response.sendRedirect("courses");
                break;
            case "create":
            case "update":
                final Course course = "create".equals(action) ?
                        new Course() :
                        controller.get(getId(request));
                request.setAttribute("course", course);
                request.getRequestDispatcher("/courseForm.jsp").forward(request, response);
                break;
            case "filter":
                float startCost = Float.parseFloat(request.getParameter("startCost"));
                float endCost = Float.parseFloat(request.getParameter("endCost"));
                request.setAttribute("courses", controller.getBetween(startCost,endCost));
                request.getRequestDispatcher("/courses.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("courses", controller.getAll());
                request.getRequestDispatcher("/courses.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
