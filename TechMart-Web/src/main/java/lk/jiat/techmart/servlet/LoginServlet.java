package lk.jiat.techmart.servlet;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import lk.jiat.techmart.entity.User;
import lk.jiat.techmart.enums.UserRole;
import lk.jiat.techmart.service.UserService;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @EJB
    private UserService userService;


    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        String email =
                request.getParameter("email");

        String password =
                request.getParameter("password");


        Optional<User> optionalUser =
                userService.authenticate(
                        email,
                        password
                );


        if (optionalUser.isPresent()) {

            User user =
                    optionalUser.get();


            HttpSession session =
                    request.getSession(true);

            session.setAttribute(
                    "loggedUser",
                    user
            );


            if (user.getRole() == UserRole.ADMIN) {

                response.sendRedirect(
                        request.getContextPath()
                                + "/admin/dashboard.jsp"
                );

            } else {

                response.sendRedirect(
                        request.getContextPath()
                                + "/customer/dashboard.jsp"
                );
            }

        } else {

            request.setAttribute(
                    "error",
                    "Invalid email or password"
            );

            request.getRequestDispatcher(
                    "/login.jsp"
            ).forward(
                    request,
                    response
            );
        }
    }


    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher(
                "/login.jsp"
        ).forward(
                request,
                response
        );
    }
}