package ru.university.web;

import org.slf4j.Logger;
import ru.university.model.Student;
import ru.university.model.UniversityCourse;
import ru.university.repository.StudentRepository;
import ru.university.repository.UniversityCourseRepository;
import ru.university.repository.inmemory.InMemoryStudentRepository;
import ru.university.repository.inmemory.InMemoryUniversityCourseRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.university.web.SecurityUtil.authFacultyId;

public class UniCourseServlet extends HttpServlet {
    public static final Logger log = getLogger((StudentServlet.class));

    private UniversityCourseRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryUniversityCourseRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        UniversityCourse course = new UniversityCourse(id.isEmpty() ? null: Integer.valueOf(id),
                req.getParameter("name"),
                Integer.parseInt(req.getParameter("number")),
                Float.parseFloat(req.getParameter("cost")));

        log.info(course.isNew() ? "Create {}" : "Update {}", course);
        repository.save(course,authFacultyId());
        resp.sendRedirect("courses");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                repository.delete(id,authFacultyId());
                response.sendRedirect("courses");
                break;
            case "create":
            case "update":
                final UniversityCourse course = "create".equals(action)?
                        new UniversityCourse() :
                        repository.get(getId(request), authFacultyId());
                request.setAttribute("course", course);
                request.getRequestDispatcher("/courseForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("courses", repository.getAll(authFacultyId()));
                request.getRequestDispatcher("/courses.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
