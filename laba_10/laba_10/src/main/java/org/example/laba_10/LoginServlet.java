package org.example.laba_10;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.laba_10.dao.DAO;
import org.example.laba_10.dao.User;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final DAO db = DAO.getInstance();

    private static final User user = new User();

    @Override
    public void init()throws ServletException{
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isUserFound = false;
        Date currentDate = new Date();

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        try {
            ResultSet rs = db.checkUsersCount(username, password);
            rs.next();
            if (rs.getInt("count") != 0) {
                ResultSet userSet = db.checkUser(username);
                userSet.next();
                user.setLogin(userSet.getString("USER_LOG"));
                user.setRole(userSet.getString("USER_ROL"));
                isUserFound = true;
            }
            else {
                response.sendRedirect("Login.jsp?error=1");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(isUserFound){
            HttpSession session = request.getSession();
            session.setAttribute("name", user.getLogin());
            session.setAttribute("role", user.getRole());
            session.setAttribute("date", currentDate);
            session.setAttribute("current_user", user);
            session.setAttribute("db", db);
            //db.closeConnection();
            request.getRequestDispatcher("Welcome.jsp").forward(request, response);
            //response.sendRedirect("Welcome.jsp");
        }
        else {
            response.sendRedirect("Login.jsp?error=1");
        }
    }
}
