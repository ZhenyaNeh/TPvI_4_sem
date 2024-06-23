package org.example.laba_10;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.laba_10.dao.DAO;
import org.example.laba_10.dao.User;

import java.io.IOException;

@WebServlet(name = "MainServlet", value = "/MainServlet", urlPatterns = {"/MainServlet"})
public class MainServlet extends HttpServlet {
    private static final DAO db = DAO.getInstance();

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String classId = request.getParameter("id");
        db.ExecuteQuery("delete PEOPLE where ID = " + classId);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("current_user");
        request.setAttribute("name", user.getLogin());
        session.setAttribute("db", db);
        //db.closeConnection();
        response.sendRedirect("Welcome.jsp");
        //request.getRequestDispatcher("Welcome.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String people = request.getParameter("people");
        String country = request.getParameter("country");
        String count = request.getParameter("count");

        if(people.length() != 0 & country.length() != 0 & count.length() !=0){
            db.ExecuteQuery("insert into PEOPLE (PEOPLE, PEOPLE_COUNTRY, PEOPLE_COUNT) values (N'"
                    + people + "', N'" + country + "', N'" + count +"')");
        }

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("current_user");
        request.setAttribute("name", user.getLogin());
        //session.setAttribute("db", db);
        //db.closeConnection();
        response.sendRedirect("Welcome.jsp");
        //request.getRequestDispatcher("Welcome.jsp").forward(request, response);
    }
}
