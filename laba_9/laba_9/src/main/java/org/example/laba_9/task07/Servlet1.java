package org.example.laba_9.task07;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "Servlet1", value = "/Servlet1")
public class Servlet1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        request.setAttribute("name1", name);
        request.getRequestDispatcher("/Servlet2").include(request, response);
        var name2 = request.getAttribute("name2");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>" + name2 + "</h2>");
        out.println("</body></html>");
        out.close();
    }

}