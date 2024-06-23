package org.example.laba_13.Controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.example.laba_13.Command.Command;
import org.example.laba_13.Command.CommandResult;
import org.example.laba_13.Command.Factory.CommandFactory;
import org.example.laba_13.Connection.ConnectionPool;
import org.example.laba_13.Exceptions.IncorrectDataException;
import org.example.laba_13.Exceptions.ServiceException;
import org.example.laba_13.util.Page;

import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet", urlPatterns = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    private static final String COMMAND = "command";
    private static final String ERROR_MESSAGE = "error_message";
    private static final Logger LOGGER = Logger.getLogger(ControllerServlet.class.getName());

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroy();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String command = request.getParameter(COMMAND);

        LOGGER.info(COMMAND + " = " + command);

        Command action = CommandFactory.create(command);

        CommandResult commandResult;
        try {
            commandResult = action.execute(request, response);
        } catch (ServiceException | IncorrectDataException e) {
            LOGGER.error(e.getClass(), e);
            request.setAttribute(ERROR_MESSAGE, e.getClass());
            commandResult = new CommandResult(Page.ERROR_PAGE.getPage(), false);
        }

        String page = commandResult.getPage();

        if (commandResult.isRedirect()) {
            sendRedirect(response, page);
        } else {
            dispatch(request, response, page);
        }
    }

    private void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }

    private void sendRedirect(HttpServletResponse response, String page) throws IOException {
        response.sendRedirect(page);
    }
}
