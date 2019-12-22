package ru.university.web;

import org.slf4j.Logger;
import ru.university.repository.InMemoryStudentRepository;
import ru.university.repository.StudentRepository;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.slf4j.LoggerFactory.getLogger;

public class StudentServlet extends HttpServlet {
    public static final Logger log = getLogger((StudentServlet.class));

    private StudentRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryStudentRepository();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to students");
        req.getRequestDispatcher("/professors.jsp").forward(req, resp);
    }
}
