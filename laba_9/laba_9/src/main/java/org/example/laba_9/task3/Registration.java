package org.example.laba_9.task3;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.laba_9.task3.dao.DAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Enumeration;

@WebServlet("/Registration")
public class Registration extends HttpServlet {

    private final DAO db = new DAO();

    @Override
    public void init()throws ServletException{
        db.getConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userName = request.getParameter("username");
        String userPassword = request.getParameter("password");

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        PrintWriter out = response.getWriter();

        if(userName.length() != 0 && userPassword.length() != 0){
            try {
                ResultSet resultSet = db.ExecuteQuery("select count(*)[count] from Users where USER_LOG = '" + userName + "'");
                resultSet.next();
                //Integer test = resultSet.getInt("counter");
                if(resultSet.getInt("count") == 0){
                    db.addUser(userName,userPassword);
                    session.setAttribute("message", "Registration completed successfully!!\n" +
                            "Go to the account login menu");
                    response.sendRedirect("Registration.jsp");
                }
                else{
                    session.setAttribute("message", "A user with the same login already exists.");
                    response.sendRedirect("Registration.jsp?valid=1");
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        else{
            session.setAttribute("message", "Fields cannot be empty");
            response.sendRedirect("Registration.jsp?valid=1");
        }
    }
}

































