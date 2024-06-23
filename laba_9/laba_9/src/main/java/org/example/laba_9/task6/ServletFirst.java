package org.example.laba_9.task6;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ServletFirst extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Name","UserGet");
        request.getRequestDispatcher("ServletSecond").forward(request, response);
        //response.sendRedirect("ServletSecond");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("Name","UserPost");
        request.getRequestDispatcher("ServletSecond").forward(request, response);
    }
}
