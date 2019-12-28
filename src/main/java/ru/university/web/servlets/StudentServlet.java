package ru.university.web.servlets;

import org.slf4j.Logger;
import ru.university.model.Student;
import ru.university.repository.StudentRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class StudentServlet extends HttpServlet {
    public static final Logger log = getLogger((StudentServlet.class));

    private StudentRepository repository;

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        repository = new InMemoryStudentRepository();
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Student student = new Student(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name"),
                req.getParameter("address"),
                req.getParameter("email"),
                Integer.parseInt(req.getParameter("recordNumber")),
                Float.parseFloat(req.getParameter("averageRating")));

        log.info(student.isNew() ? "Create {}" : "Update {}", student);
        repository.save(student);
        resp.sendRedirect("students");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                repository.delete(id);
                response.sendRedirect("students");
                break;
            case "create":
            case "update":
                final Student student = "create".equals(action) ?
                        new Student() :
                        repository.get(getId(request));
                request.setAttribute("student", student);
                request.getRequestDispatcher("/studentForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("students", repository.getAll());
                request.getRequestDispatcher("/students.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
