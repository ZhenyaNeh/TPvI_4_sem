package org.example.laba_10;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.laba_10.dao.User;

import java.io.IOException;

@WebFilter(filterName = "FilterWelcome", urlPatterns = {"/Welcome.jsp"})
public class FilterWelcome implements Filter {
    public void init(FilterConfig config) throws ServletException {

    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest  , ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("current_user");
        if (user == null) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
        } else {
            servletRequest.setAttribute("name", user.getLogin());
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
