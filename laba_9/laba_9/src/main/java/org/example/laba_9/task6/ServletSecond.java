package org.example.laba_9.task6;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalTime;

@WebServlet(name = "ServletSecond", value = "/ServletSecond")
public class ServletSecond extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getAttribute("Name").toString();
        request.setAttribute("Name", name);
        //request.getRequestDispatcher("Registration.jsp").forward(request, response);
        response.sendRedirect("Registration.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        String name = request.getAttribute("Name").toString();
        out.println("<h4>Текущее время: "+ LocalTime.now()+"</h4>");
        out.println("<h3>"+ name +"</h3>");
    }
}
