package lk.jiat.techmart.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/log-test")
public class LogTestServlet extends HttpServlet {

    private static final Logger LOGGER =
            Logger.getLogger(LogTestServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("SYSTEM OUT TEST");
        LOGGER.info("LOGGER INFO TEST");
        LOGGER.warning("LOGGER WARNING TEST");
        LOGGER.severe("LOGGER SEVERE TEST");

        response.getWriter().println("Log Test Completed");
    }
}