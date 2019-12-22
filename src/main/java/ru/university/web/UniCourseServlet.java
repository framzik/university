package ru.university.web;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UniCourseServlet extends HttpServlet {
    public static final Logger log = getLogger((StudentServlet.class));
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("redirect to courses");
        req.getRequestDispatcher("/professors.jsp").forward(req, resp);
    }
}
