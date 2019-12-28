package ru.university.web.servlets;

import org.slf4j.Logger;
import ru.university.model.Faculty;
import ru.university.repository.FacultyRepository;
import ru.university.repository.inmemory.InMemoryFacultyRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class FacultyServlet extends HttpServlet {
    public static final Logger log=getLogger(FacultyServlet.class);

    private FacultyRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryFacultyRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Faculty faculty = new Faculty(id.isEmpty() ? null : Integer.valueOf(id),
                req.getParameter("name"));

        log.info(faculty.isNew() ? "Create {}" : "Update {}", faculty);
        repository.save(faculty);
        resp.sendRedirect("faculties");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                repository.delete(id);
                response.sendRedirect("faculties");
                break;
            case "create":
            case "update":
                final Faculty faculty = "create".equals(action) ?
                        new Faculty() :
                        repository.get(getId(request));
                request.setAttribute("faculty", faculty);
                request.getRequestDispatcher("/facultyForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("faculties", repository.getAll());
                request.getRequestDispatcher("/faculties.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
