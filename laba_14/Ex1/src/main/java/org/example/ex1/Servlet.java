package org.example.ex1;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Servlet", value = "/Servlet")
public class Servlet extends HttpServlet {
    private final DAO db = DAO.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");

        try {
            ResultSet rs = db.countPeople(login);
            rs.next();
            if (rs.getInt("count") == 1) {
                int price = db.sumPeople(login);
                request.setAttribute("price", price);
                request.getRequestDispatcher("Info.jsp").forward(request, response);
            }
            else {
                ServletContext servletContext = getServletContext();
                RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher("/error.jsp");
                request.setAttribute("name", "Ошибка при входе");
                requestDispatcher.forward(request, response);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}