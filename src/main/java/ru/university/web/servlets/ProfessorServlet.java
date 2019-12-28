package ru.university.web.servlets;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class ProfessorServlet extends HttpServlet {
    public static final Logger log = getLogger(ProfessorServlet.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.debug("redirect to professors");
        req.getRequestDispatcher("/professors.jsp").forward(req, resp);
    }
}
