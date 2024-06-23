package task2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

@WebServlet(name = "task2", value = "/task2")
public class Task2 extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Current Time</title></head>");
        out.println("<body>");

        out.println("<h1>Current Time: " + new Date() + "</h1>");

        out.println("<h2>Request Information:</h2>");
        out.println("<p>Protocol: " + request.getProtocol() + "</p>");
        out.println("<p>Client IP Address: " + request.getRemoteAddr() + "</p>");
        out.println("<p>Client Hostname: " + request.getRemoteHost() + "</p>");
        out.println("<p>Method: " + request.getMethod() + "</p>");
        out.println("<p>URL: " + request.getRequestURL() + "</p>");

        out.println("<h2>Title Information<h2>");
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            out.println("<li>" + headerName + ": " + request.getHeader(headerName) + "</li>");
        }
        out.println("</ul>");

        out.println("</body></html>");
    }

    public void destroy() {
    }
}
