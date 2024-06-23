package org.example.laba_9.task5;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.laba_9.task3.dao.User;

import java.io.IOException;
import java.util.Date;

@WebFilter(filterName = "FilterLogin", urlPatterns = {"/Welcome.jsp"},
        initParams = { @WebInitParam(name = "LOGIN_PATH", value = "/Login.jsp") })
public class FilterLogin implements Filter {
    private String registerPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
    {
        registerPath = filterConfig.getInitParameter("LOGIN_PATH");
    }
    @Override
    public void doFilter(ServletRequest servletRequest  , ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;



        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if (user == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(request.getContextPath() + registerPath);
        } else {
            servletRequest.setAttribute("name", user.getLogin());
            servletRequest.setAttribute("role", user.getRole());
            servletRequest.setAttribute("date", new Date());
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
