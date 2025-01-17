package org.example.laba_9.task4;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ServletCookie", value = "/ServletCookie")
public class ServletCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String cookieLoginName = "login";
        String cookieRoleName = "role";
        Cookie cookieLogin = null;
        Cookie cookieRole = null;

        if (cookies != null) {
            for (Cookie c: cookies) {
                if (cookieLoginName.equals(c.getName()))
                    cookieLogin = c;
                if (cookieRoleName.equals((c.getName())))
                    cookieRole = c;
            }
        }
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>From cookies:</h2>");
            out.println("<p>Login: " + cookieLogin.getValue() + "</p>");
            out.println("<p>Role:  " + cookieRole.getValue() + "</p>");
            out.println("</html></body>");

        }
    }

}
