package org.example.laba_9.task3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import org.example.laba_9.task3.dao.DAO;
import org.example.laba_9.task3.dao.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Base64;
import java.util.Date;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private final DAO db = new DAO();

    @Override
    public void init()throws ServletException{
        db.getConnection();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isUserFound = false;
        java.util.Date currentDate = new Date();
        User user = new User();

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
                user = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        HttpSession session = request.getSession();
        session.setAttribute("current_user", user);

        if(isUserFound){
            Cookie cookieLogin = new Cookie("login", user.getLogin());
            Cookie cookieRole = new Cookie("role", user.getRole());
            response.addCookie(cookieRole);
            response.addCookie(cookieLogin);

            session.setAttribute("username", user.getLogin());
            session.setAttribute("role", user.getRole());
            session.setAttribute("date", new java.util.Date());
            //response.sendRedirect(request.getContextPath() + "/Welcome.jsp");
            response.sendRedirect("Welcome.jsp");
        }
        else {
            response.sendRedirect("Login.jsp?error=1");
        }
    }
}
